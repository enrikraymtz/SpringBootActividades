package com.enrikraymtz.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.enrikraymtz.springboot.model.Autor;
import com.enrikraymtz.springboot.service.AutorService;
import com.enrikraymtz.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class AutorRestApiController {
	
	@Autowired
	@Qualifier("autorService")
	private AutorService autorService;
	
	@RequestMapping(value="/autor/", method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listAllAutor(){
		List<Autor> autor = autorService.findAllAutor();
		if(autor.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Autor>>(autor, HttpStatus.OK);
	}
	
	@RequestMapping(value="/autor/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProducto(@PathVariable("id") Long id){
		Autor autor = autorService.findById(id);
		if(autor == null){
			return new ResponseEntity(new CustomErrorType("Autor con id " + id + " no encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
	
	@RequestMapping(value="/autor/", method = RequestMethod.POST)
	public ResponseEntity<?> crearAutor(@RequestBody Autor autor, UriComponentsBuilder ucBuilder){
		if(autorService.isAutorExist(autor)){
			return new ResponseEntity(new CustomErrorType("Imposible guardar. Autor con nombre "+autor.getNombre() +" ya existe"), HttpStatus.CONFLICT);
		}
		autorService.guardarAutor(autor);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/autor/{id}").buildAndExpand(autor.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/autor/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarAutor(@PathVariable("id") Long id, @RequestBody Autor autor){
		Autor currentAutor = autorService.findById(id);
		if(autor == null){
			return new ResponseEntity(new CustomErrorType("Imposible actualizar. Autor con id "+ id +" no encontrado"), HttpStatus.NOT_FOUND);
		}
		currentAutor.setNombre(autor.getNombre());
		autorService.actualizarAutor(currentAutor);
		return new ResponseEntity<Autor>(currentAutor, HttpStatus.OK);
	}
	
	@RequestMapping(value="/autor/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<?> eliminarAutor(@PathVariable("id") Long id){
		Autor autor = autorService.findById(id);
		if(autor == null){
			return new ResponseEntity(new CustomErrorType("Imposible eliminar. Autor con id "+id+" no encontrado"), HttpStatus.NOT_FOUND);
		}
		autorService.eliminarAutorById(id);
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);		
	}

	@RequestMapping(value="/autor/", method = RequestMethod.DELETE)
	public ResponseEntity<Autor> eliminarAllAutor(){
		autorService.eliminarTodosAutor();
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);
	}
}
