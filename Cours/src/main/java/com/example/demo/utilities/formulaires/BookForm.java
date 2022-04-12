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

	private long id;
	@NotEmpty(message = "Paramètres non renseigné")
	private String nom;
	private long nb_exemplaires;
	private long id_auteur;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getNb_exemplaires() {
		return nb_exemplaires;
	}

	public void setNb_exemplaires(long nb_exemplaires) {
		this.nb_exemplaires = nb_exemplaires;
	}

	public long getId_auteur() {
		return id_auteur;
	}

	public void setId_auteur(long id_auteur) {
		this.id_auteur = id_auteur;
	}
}
