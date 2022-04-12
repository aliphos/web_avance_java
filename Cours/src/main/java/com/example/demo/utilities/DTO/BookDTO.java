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
	private int id;
	private String nom;
	private long nb_exemplaires;
	private int id_auteur;

	public int getId_auteur() {
		return id_auteur;
	}

	public void setId_auteur(int id_auteur) {
		this.id_auteur = id_auteur;
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


}
