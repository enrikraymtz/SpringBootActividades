package com.enrikraymtz.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.enrikraymtz.springboot.model.Libro;
import com.enrikraymtz.springboot.service.LibroService;
import com.enrikraymtz.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class LibroRestApiController {
	
	@Autowired
	LibroService libroService;
	
	//Get of all products
	@RequestMapping(value="/libro/", method = RequestMethod.GET)
	public ResponseEntity<List<Libro>> listAllProductos(){
		List<Libro> libros = libroService.findAllLibros();
		if(libros.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Libro>> (libros, HttpStatus.OK);
	}
	
	//Get for id product
	@RequestMapping(value="/libro/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getLibro(@PathVariable("id") Long id){
		Libro libro = libroService.findById(id);
		if(libro == null){
			return new ResponseEntity(new CustomErrorType("Libro con id " + id +" no encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}
	
	//Save of product
	@RequestMapping(value="/libro/", method = RequestMethod.POST)
	public ResponseEntity<?> crearProducto(@RequestBody Libro libro, UriComponentsBuilder ucBuilder){
		if(libroService.isLibroExist(libro)){
			return new ResponseEntity(new CustomErrorType("Imposible guardar. El libro con nombre "+ libro.getTitulo()+" ya existe."), HttpStatus.CONFLICT);
		}
		libroService.guardarLibro(libro);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/producto/{id}").buildAndExpand(libro.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	//Update of product for id	
	@RequestMapping(value="/libro/{id}", method= RequestMethod.PUT)
	public ResponseEntity<?> actualizarLibro(@PathVariable("id") Long id, @RequestBody Libro libro){
		Libro currentLibro = libroService.findById(id);
		if(currentLibro == null){
			return new ResponseEntity<>(new CustomErrorType("Imposible actualizar. Libro con id " + id + " no encontrado"), HttpStatus.NOT_FOUND);
		}
		currentLibro.setTitulo(libro.getTitulo());
		currentLibro.setIsbn(libro.getIsbn());
		currentLibro.setPrecio(libro.getPrecio());
		currentLibro.setAutor(libro.getAutor());
		
		libroService.actualizarLibro(currentLibro);
		return new ResponseEntity<Libro>(currentLibro, HttpStatus.OK);
	}
	
	//Delete of product for id
	@RequestMapping(value="/libro/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarLibro(@PathVariable("id") Long id){
		Libro libro = libroService.findById(id);
		if(libro == null){
			return new ResponseEntity(new CustomErrorType("Imposible eliminar. Libro con id "+ id +" no encontrado"), HttpStatus.NOT_FOUND);
		}
		libroService.eliminarLibroById(id);
		return new ResponseEntity<Libro> (HttpStatus.NO_CONTENT);
	}
	
	//Delete all products
	@RequestMapping(value="/libro/", method = RequestMethod.DELETE)
	public ResponseEntity<Libro> eliminarTodosLibro(){
		libroService.eliminarTodosLibro();
		return new ResponseEntity<Libro>(HttpStatus.NO_CONTENT);
	}
}
