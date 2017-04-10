package com.enrikraymtz.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrikraymtz.springboot.model.Autor;

@Repository("autorRepository")
public interface AutorRepository extends JpaRepository<Autor, Long> {
	Autor findByNombre(String nombre);
}
