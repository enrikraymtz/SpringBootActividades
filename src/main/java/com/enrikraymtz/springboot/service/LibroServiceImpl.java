package com.enrikraymtz.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enrikraymtz.springboot.model.Libro;
import com.enrikraymtz.springboot.repository.LibroRepository;

@Service("libroService")
@Transactional
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	public Libro findById(Long id){
		return libroRepository.findOne(id);
	}
	
	public Libro findByTitulo(String titulo){
		return libroRepository.findByTitulo(titulo);
	}
	
	public void guardarLibro(Libro libro){
		libroRepository.save(libro);
	}
	
	public void actualizarLibro(Libro libro){
		guardarLibro(libro);
	}

	public void eliminarLibroById(Long id){
		libroRepository.delete(id);
	}
	
	public void eliminarTodosLibro(){
		libroRepository.deleteAll();
	}
	
	public List<Libro> findAllLibros(){
		return libroRepository.findAll();
	}
	
	public boolean isLibroExist(Libro libro){
		return findByTitulo(libro.getTitulo()) != null;
	}
}
