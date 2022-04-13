package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Author;
import com.example.demo.utilities.DTO.AuthorDTO;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
	public AuthorController(IAuthorInterface service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "Créer un nouvel Auteur")
	@PostMapping("/api/authors")
	public ResponseDTO<AuthorDTO> saveAuthor(@Valid @RequestBody AuthorForm userForm, BindingResult bindingResult) {
		log.info("Un utilisateur a créer un nouvel auteur");
		return service.saveAuthor(userForm,bindingResult);
	}
	
	@ApiOperation(value = "Récupérer l'ensemble des auteurs")
	@GetMapping("/api/authors")
	public List<AuthorDTO> getAllUsers(){
		log.info("Un utilisateur a récupéré l'ensemble des auteurs");
		return service.getAllUsers();
		
	}
	@ApiOperation(value = "Récupérer un auteur via son identifiant")
	@GetMapping("/api/authors/{id}")
	public AuthorDTO getAuthorById(@Valid @PathVariable int id){
		log.info("Un utilisateur a récupéré un auteur via son identifiant");
		return service.getAuthorDTOById(id);
		
	}

	@ApiOperation(value = "Supprimer un auteur via son identifiant")
	@DeleteMapping("/api/authors/{id}")
	public ResponseDTO<String> deleteAuthorById(@PathVariable @Valid int id){
		log.info("Un utilisateur a supprimé un auteur via son identifiant");
		return service.deleteAuthorById((long)id);

	}

	@ApiOperation(value = "Modifier un  Auteur")
	@PutMapping("/api/authors")
	public ResponseDTO<AuthorDTO> updateAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
		log.info("Un utilisateur a modifié un auteur");
		return service.updateAuthor(author,bindingResult);
	}

	
}
