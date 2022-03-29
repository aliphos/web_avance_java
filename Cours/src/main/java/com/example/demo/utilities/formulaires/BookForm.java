package com.example.demo.utilities.formulaires;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.models.Author;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookForm {
	@NotEmpty(message = "Paramètres non renseigné")
	private String nom_livre;
	private long nb_exemplaires;
	private AuthorForm author;
}
