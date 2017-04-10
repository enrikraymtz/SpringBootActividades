package com.enrikraymtz.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enrikraymtz.springboot.model.Autor;
import com.enrikraymtz.springboot.repository.AutorRepository;

@Service("autorService")
@Transactional
public class AutorServiceImpl implements AutorService {
	
	@Autowired
	@Qualifier("autorRepository")
	private AutorRepository autorRepository;
	
	public Autor findById(Long id){
		return autorRepository.findOne(id);
	}
	
	public Autor findByNombre(String nombre){
		return autorRepository.findByNombre(nombre);
	}
	
	public void guardarAutor(Autor autor){
		autorRepository.save(autor);
	}
	
	public void actualizarAutor(Autor autor){
		guardarAutor(autor);
	}
	
	public void eliminarAutorById(Long id){
		autorRepository.delete(id);
	}
	
	public void eliminarTodosAutor(){
		autorRepository.deleteAll();
	}
	
	public List<Autor> findAllAutor(){
		return autorRepository.findAll();
	}
	
	public boolean isAutorExist(Autor autor){
		return findByNombre(autor.getNombre()) != null;
	}
	

}
