package com.example.demo.mapper;

import com.example.demo.utilities.DTO.BookDTO;
import org.mapstruct.Mapper;

import com.example.demo.models.Book;
import com.example.demo.utilities.formulaires.BookForm;
import org.mapstruct.Mapping;

@Mapper
public interface IBookMapper {
	Book BookFormToBook(BookForm bookForm);
	BookDTO BookToBookDTO(Book book);
}
