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

import com.enrikraymtz.springboot.model.User;
import com.enrikraymtz.springboot.repository.UserRepository;
import com.enrikraymtz.springboot.service.UsuarioRoleService;
import com.enrikraymtz.springboot.service.UsuarioService;
import com.enrikraymtz.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("usuarioRoleService")
	private UsuarioRoleService usuarioRoleService;
	
	
	@RequestMapping(value = "/usuarios/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAllUsers(){
		List<User> users = usuarioService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findUser(@PathVariable("id") Long id){
		User usuario = usuarioService.findById(id);
		if(usuario == null){
			return new ResponseEntity(new CustomErrorType("Error, usuario con id "+ id +" no encontrado! "), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(usuario, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/usuarios/", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User usuario, UriComponentsBuilder ucBuilder){
		if(usuarioService.isUsuarioExist(usuario)){
			return new ResponseEntity(new CustomErrorType("Usuario con username " + usuario.getUsername() + " ya existe. "), HttpStatus.CONFLICT );
		}
		usuarioService.addUsuario(usuario);
		
		HttpHeaders headers =  new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getUsername()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/usuarios/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User usuario ){
		User updUsuario = usuarioService.findById(id);
		if(updUsuario == null){
			return new ResponseEntity(new CustomErrorType("Imposible actualizar. Usuario con id "+ id + " no encontrado" ), HttpStatus.NOT_FOUND );
		}
		updUsuario.setPassword(usuario.getPassword());
		updUsuario.setRoles(usuario.getRoles());
		
		usuarioService.updateUsuario(updUsuario);
		return new ResponseEntity<User>(updUsuario, HttpStatus.OK);			
	}
	
	@RequestMapping(value="/usuarios/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		User usuario = usuarioService.findById(id);
		if(null == usuario){
			return new ResponseEntity(new CustomErrorType("Imposible eliminar. Usuario con id "+ id + " no encontrado!" ), HttpStatus.NOT_FOUND);
		}
		
		usuarioRoleService.eliminarRole(id);
		//usuarioService.deleteUsuario(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
}
