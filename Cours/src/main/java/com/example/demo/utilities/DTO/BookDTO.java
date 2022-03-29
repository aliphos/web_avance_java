package com.example.demo.utilities.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
	private String nom_livre;
	private long nb_exemplaires;
	private AuthorDTO authorDTO;
}
