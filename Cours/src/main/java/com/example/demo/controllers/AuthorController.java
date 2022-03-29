package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.utilities.DTO.AuthorDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/authors")
	public ResponseDTO<AuthorDTO> saveUser(@Valid @RequestBody AuthorForm userForm, BindingResult bindingResult) {
		return service.saveAuthor(userForm,bindingResult);
	}
	
	@ApiOperation(value = "Récupérer l'ensemble des auteurs")
	@GetMapping("/authors")
	public List<AuthorDTO> getAllUsers(){
		return service.getAllUsers();
		
	}
	@ApiOperation(value = "Récupérer un auteur via son identifiant")
	@GetMapping("/authors/{id}")
	public AuthorDTO getUserById(@Valid @PathVariable int id){
		return service.getAuthorDTOById(id);
		
	}
	
}
