package com.enrikraymtz.springboot.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.enrikraymtz.springboot.model.User;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<User, Serializable > {
	public User findByUsername(String username);

}
