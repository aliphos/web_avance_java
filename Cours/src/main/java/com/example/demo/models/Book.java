package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIVRE",nullable = false , unique = true)
    private long id;
    @Column(name = "LL_LIVRE")
    private String nom_livre;
    @Column(name = "NB_EXEMPLAIRE")
    private long nb_exemplaires;
    @ManyToOne
	@JoinColumn(name="id_author")
    private Author author;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom_livre() {
		return nom_livre;
	}

	public void setNom_livre(String nom_livre) {
		this.nom_livre = nom_livre;
	}

	public long getNb_exemplaires() {
		return nb_exemplaires;
	}

	public void setNb_exemplaires(long nb_exemplaires) {
		this.nb_exemplaires = nb_exemplaires;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
