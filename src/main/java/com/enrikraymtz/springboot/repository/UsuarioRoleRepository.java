package com.enrikraymtz.springboot.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrikraymtz.springboot.model.UserRole;

@Repository("usuarioRoleRepository")
public interface UsuarioRoleRepository extends JpaRepository<UserRole, Serializable> {
	
}
