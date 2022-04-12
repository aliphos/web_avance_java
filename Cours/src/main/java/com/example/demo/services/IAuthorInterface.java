package com.example.demo.services;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.example.demo.models.Author;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.DTO.AuthorDTO;
import com.example.demo.utilities.formulaires.AuthorForm;

public interface IAuthorInterface {
	 public ResponseDTO<AuthorDTO> saveAuthor(AuthorForm user, BindingResult bindingResult);
	 public void saveAuthor(Author user);
	 public List<AuthorDTO> getAllUsers();
	 public Author getAuthorById(long id);
	 public AuthorDTO getAuthorDTOById(long id);
	 public ResponseDTO<String> deleteAuthorById(long id);
	 public ResponseDTO<AuthorDTO> updateAuthor(Author authormodified, BindingResult bindingResult);
	 String checkErreur(BindingResult bindingResult);

}
