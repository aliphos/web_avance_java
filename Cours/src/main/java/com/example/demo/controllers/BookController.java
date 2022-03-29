package com.example.demo.controllers;

import java.util.List;

import com.example.demo.utilities.DTO.BookDTO;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.formulaires.BookForm;
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
	public BookController(IBooksInterface service, IAuthorInterface user) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Créer un nouveau Livre")
	@PostMapping("/books")
	public ResponseDTO<BookDTO> saveBook(@Valid @RequestBody BookForm bookForm, BindingResult bindingResult) {
		return service.saveBook(bookForm,bindingResult);
	}

	@ApiOperation(value = "Récupérer l'ensemble des auteurs")
	@GetMapping("/books")
	public List<BookDTO> getAllBooks(){
		return service.getAllBooks();

	}
	@ApiOperation(value = "Récupérer un auteur via son identifiant")
	@GetMapping("/books/{id}")
	public BookDTO getBookById(@Valid @PathVariable int id){
		return service.getBookDTOById(id);

	}
	
}