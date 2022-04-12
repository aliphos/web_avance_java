package com.example.demo.utilities.formulaires;

import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorForm {
	@NotEmpty(message = "Paramètres non renseigné")
	private String nom;
	@NotEmpty(message = "Paramètres non renseigné")
	private String prenom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
