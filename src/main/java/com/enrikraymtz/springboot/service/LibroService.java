package com.enrikraymtz.springboot.service;

import java.util.List;

import com.enrikraymtz.springboot.model.Libro;

public interface LibroService {

	Libro findById(Long id);
	
	Libro findByTitulo(String titulo);
	
	void guardarLibro(Libro libro);
	
	void actualizarLibro(Libro libro);
	
	void eliminarLibroById(Long id);
	
	void eliminarTodosLibro();
	
	List<Libro> findAllLibros();
	
	boolean isLibroExist(Libro libro);
}
