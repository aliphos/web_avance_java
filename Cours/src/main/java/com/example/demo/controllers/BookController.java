package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Book;
import com.example.demo.utilities.DTO.BookDTO;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.formulaires.BookForm;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.IBooksInterface;
import com.example.demo.services.IAuthorInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController

@Api(value = "SwaggerRestController",description ="Gère les Livres")
public class BookController {
	private IBooksInterface service;
	private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
	public BookController(IBooksInterface service, IAuthorInterface user) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Créer un nouveau Livre")
	@PostMapping("/api/books")
	public ResponseDTO<BookDTO> saveBook(@Valid @RequestBody BookForm bookForm, BindingResult bindingResult) {
		log.info("Un utilisateur a créé un nouveau livre");
		return service.saveBook(bookForm,bindingResult);
	}

	@ApiOperation(value = "Récupérer l'ensemble des auteurs")
	@GetMapping("/api/books")
	public List<BookDTO> getAllBooks(){
		log.info("Un utilisateur a récupéré l'ensemble des livres");
		return service.getAllBooks();

	}
	@ApiOperation(value = "Récupérer un auteur via son identifiant")
	@GetMapping("/api/books/{id}")
	public BookDTO getBookById(@Valid @PathVariable int id){
		log.info("Un utilisateur a récupéré un livre via son identifiant");
		return service.getBookDTOById(id);

	}

	@ApiOperation(value = "Supprimer un livre via son identifiant")
	@DeleteMapping("/api/books/{id}")
	public ResponseDTO<String> deleteBookById(@PathVariable @Valid int id){
		log.info("Un utilisateur a supprimé un livre via son identifiant");
		return service.deleteBookById((long)id);

	}
	@ApiOperation(value = "Modifier un  Livre")
	@PutMapping("/api/books")
	public ResponseDTO<BookDTO> updateBook(@Valid @RequestBody BookForm book, BindingResult bindingResult) {
		log.info("Un utilisateur a modifié un livre ");
		return service.updateBook(book,bindingResult);
	}
	
}