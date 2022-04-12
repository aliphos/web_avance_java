package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Author;
import com.example.demo.utilities.DTO.AuthorDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.IAuthorInterface;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.formulaires.AuthorForm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@Api(value = "SwaggerRestController",description ="Mon controleur")
public class AuthorController {
	private IAuthorInterface service;
	public AuthorController(IAuthorInterface service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "Créer un nouvel Auteur")
	@PostMapping("/api/authors")
	public ResponseDTO<AuthorDTO> saveAuthor(@Valid @RequestBody AuthorForm userForm, BindingResult bindingResult) {
		return service.saveAuthor(userForm,bindingResult);
	}
	
	@ApiOperation(value = "Récupérer l'ensemble des auteurs")
	@GetMapping("/api/authors")
	public List<AuthorDTO> getAllUsers(){
		return service.getAllUsers();
		
	}
	@ApiOperation(value = "Récupérer un auteur via son identifiant")
	@GetMapping("/api/authors/{id}")
	public AuthorDTO getAuthorById(@Valid @PathVariable int id){
		return service.getAuthorDTOById(id);
		
	}

	@ApiOperation(value = "Supprimer un auteur via son identifiant")
	@DeleteMapping("/api/authors/{id}")
	public ResponseDTO<String> deleteAuthorById(@PathVariable @Valid int id){
		return service.deleteAuthorById((long)id);

	}

	@ApiOperation(value = "Modifier un  Auteur")
	@PutMapping("/api/authors")
	public ResponseDTO<AuthorDTO> updateAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
		return service.updateAuthor(author,bindingResult);
	}

	
}
