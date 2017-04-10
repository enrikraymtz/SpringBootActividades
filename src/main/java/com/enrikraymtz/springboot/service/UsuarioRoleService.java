package com.enrikraymtz.springboot.service;

import com.enrikraymtz.springboot.model.UserRole;

public interface UsuarioRoleService {
	
	public UserRole findById(Long id);
	
	public void addUserRole(UserRole userRole);
	
	public void updateUserRole(UserRole userRole);
	
	public void eliminarRole(Long id);
	
}
