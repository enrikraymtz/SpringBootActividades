package com.enrikraymtz.springboot.service;

import java.util.List;

import com.enrikraymtz.springboot.model.Autor;

public interface AutorService {
	
	Autor findById(Long id);
	
	Autor findByNombre(String nombre);
	
	void guardarAutor(Autor autor);
	
	void actualizarAutor(Autor autor);
	
	void eliminarAutorById(Long id);
	
	void eliminarTodosAutor();
	
	List<Autor> findAllAutor();
	
	boolean isAutorExist(Autor autor);	
	
}
