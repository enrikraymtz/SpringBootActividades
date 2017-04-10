package com.enrikraymtz.springboot.service;

import java.util.List;

import com.enrikraymtz.springboot.model.User;

public interface UsuarioService {
	
	public User findById(Long id);
	
	public User findByUsername(String username);
	
	public List<User> findAllUsers();
	
	public void addUsuario(User user);
	
	public void updateUsuario(User user);
	
	public void deleteUsuario(Long id);
	
	public boolean isUsuarioExist(User user);
	
}
