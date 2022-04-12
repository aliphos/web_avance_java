package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AUTHOR", nullable = false, unique = true)
	private long id;
	@Column(name = "LL_NOM", nullable = true)
	private String nom;
	@Column(name = "LL_PRENOM", nullable = true)
	private String prenom;
	@OneToMany(mappedBy="auteur", cascade = CascadeType.REMOVE)
	private List<Book> books;


	public Book getBooks() {
		return (Book) books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {this.nom = nom;}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}