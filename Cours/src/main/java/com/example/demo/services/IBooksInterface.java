package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.utilities.DTO.BookDTO;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.formulaires.BookForm;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IBooksInterface {
	String getBooks(String userId,String search,String postcode);
	public ResponseDTO<BookDTO> saveBook(BookForm bookForm, BindingResult bindingResult);
	public List<BookDTO> getAllBooks();
	public BookDTO getBookDTOById(long id);
	String checkErreur(BindingResult bindingResult);

}
