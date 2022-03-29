package com.example.demo.mapper;

import com.example.demo.models.Author;
import org.mapstruct.Mapper;

import com.example.demo.utilities.DTO.AuthorDTO;
import com.example.demo.utilities.formulaires.AuthorForm;

@Mapper
public interface IAuthorMapper {
	Author AuthorFormToAuthor(AuthorForm userForm);
	AuthorDTO AuthorToAuthorDTO(Author user);
}
