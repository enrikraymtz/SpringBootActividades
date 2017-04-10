package com.enrikraymtz.springboot.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Libro")
public class Libro implements Serializable {
	
	//private Autor autor;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long libro_id;
	
	@NotEmpty
	@Column(name="ISBN", nullable=false)
	private String isbn;
	
	@Column(name="TITULO", nullable=false)
	private String titulo;
	
	@Column(name="PRECIO", nullable=false)
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name ="autor_id")
	private Autor autor;
	
	public Autor getAutor() {
	   return autor;
	}
	
	public void setAutor(Autor autor) {
	   this.autor = autor;
	}
	
	public Libro() {
	}

	public Libro(String isbn, String titulo, Double precio) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.precio = precio;
	}

	public Long getId() {
		return libro_id;
	}

	public void setId(Long id) {
		this.libro_id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
/*
	@ManyToOne
    @JoinColumn(name = "autor_id")
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
	*/
}
