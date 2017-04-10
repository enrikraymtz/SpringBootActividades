package com.enrikraymtz.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrikraymtz.springboot.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
	Libro findByTitulo(String titulo);
}
