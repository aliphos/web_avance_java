package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Book;
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
	@PostMapping("/api/books")
	public ResponseDTO<BookDTO> saveBook(@Valid @RequestBody BookForm bookForm, BindingResult bindingResult) {
		return service.saveBook(bookForm,bindingResult);
	}

	@ApiOperation(value = "Récupérer l'ensemble des auteurs")
	@GetMapping("/api/books")
	public List<BookDTO> getAllBooks(){
		return service.getAllBooks();

	}
	@ApiOperation(value = "Récupérer un auteur via son identifiant")
	@GetMapping("/api/books/{id}")
	public BookDTO getBookById(@Valid @PathVariable int id){
		return service.getBookDTOById(id);

	}

	@ApiOperation(value = "Supprimer un livre via son identifiant")
	@DeleteMapping("/api/books/{id}")
	public ResponseDTO<String> deleteBookById(@PathVariable @Valid int id){
		return service.deleteBookById((long)id);

	}
	@ApiOperation(value = "Modifier un  Livre")
	@PutMapping("/api/books")
	public ResponseDTO<BookDTO> updateBook(@Valid @RequestBody BookForm book, BindingResult bindingResult) {
		return service.updateBook(book,bindingResult);
	}
	
}