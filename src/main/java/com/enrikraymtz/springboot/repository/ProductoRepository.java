package com.enrikraymtz.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrikraymtz.springboot.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	Producto findByNombre(String nombre);
}
