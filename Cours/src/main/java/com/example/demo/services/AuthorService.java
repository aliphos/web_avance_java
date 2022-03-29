package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.Author;
import com.example.demo.utilities.DTO.AuthorDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.demo.mapper.IAuthorMapper;
import com.example.demo.repositories.IAuthorRepository;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.formulaires.AuthorForm;

@Service
public class AuthorService implements IAuthorInterface {
	private IAuthorRepository userRepo;
	private IAuthorMapper mapper;

	public AuthorService(IAuthorRepository repository, IAuthorMapper mapper) {
		super();
		this.userRepo = repository;
		this.mapper = mapper;
	}
	
	public ResponseDTO<AuthorDTO> saveAuthor(AuthorForm userForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			String erreurs = checkErreur(bindingResult);
			return new ResponseDTO<AuthorDTO>(null,409,erreurs);
		}
		Author user = mapper.AuthorFormToAuthor(userForm);
		AuthorDTO userDTO = mapper.AuthorToAuthorDTO(userRepo.save(user));
	    return new ResponseDTO<AuthorDTO>(userDTO,201,"Created");
	}
	public void saveAuthor(Author user) {
		userRepo.save(user);
	}
	
	public List<AuthorDTO> getAllUsers(){
		return userRepo.findAll().stream().map(user->mapper.AuthorToAuthorDTO(user)).collect(Collectors.toList());
		
	}

	
	public AuthorDTO getAuthorDTOById(long id){
		return mapper.AuthorToAuthorDTO(userRepo.findById(id).get());
		
	}
	public Author getAuthorById(long id){
		return userRepo.findById(id).get();
		
	}
	public String checkErreur(BindingResult bindingResult) {
		
		String retour = "";
		for (ObjectError error : bindingResult.getAllErrors()) {
			retour += error.getDefaultMessage();
			retour += "\n";
		}
		
		
		return retour;
	}


	
	
}
