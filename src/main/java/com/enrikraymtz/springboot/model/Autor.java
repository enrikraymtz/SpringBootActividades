package com.enrikraymtz.springboot.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name="Autor")
public class Autor implements Serializable{
	//private Long id;
	//private Set<Libro> libro;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long autor_id;
	
	@NotEmpty
	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	
	public Autor() {
	}
	
	public Autor(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return autor_id;
	}

	public void setId(Long id) {
		this.autor_id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    public Set<Libro> getLibro() {
        return libro;
    }
    public void setLibro(Set<Libro> libro) {
        this.libro = libro;
    }
    @Override
    public String toString() {
        String result = String.format(
                "Autor[id=%d, name='%s']%n",
                id, nombre);
        if (libro != null) {
            for(Libro libro : libro) {
                result += String.format(
                        "Book[id=%d, name='%s']%n",
                        libro.getId(), libro.getTitulo());
            }
        }
        return result;
    }
	*/
}
