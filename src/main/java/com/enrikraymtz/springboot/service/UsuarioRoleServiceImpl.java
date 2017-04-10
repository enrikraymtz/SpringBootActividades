package com.enrikraymtz.springboot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enrikraymtz.springboot.model.UserRole;
import com.enrikraymtz.springboot.repository.UsuarioRoleRepository;

@Service("usuarioRoleService")
@Transactional
public class UsuarioRoleServiceImpl implements UsuarioRoleService {

	@Autowired
	@Qualifier("usuarioRoleRepository")
	private UsuarioRoleRepository usuarioRoleRepository;
	
	@Override
	public UserRole findById(Long id) {
		return usuarioRoleRepository.findOne(id);
	}

	@Override
	public void addUserRole(UserRole userRole) {
		usuarioRoleRepository.save(userRole);
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		addUserRole(userRole);
	}

	@Override
	public void eliminarRole(Long id) {
		usuarioRoleRepository.delete(id);
	}

}
