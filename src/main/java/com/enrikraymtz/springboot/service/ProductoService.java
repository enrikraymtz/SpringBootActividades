package com.enrikraymtz.springboot.service;

import java.util.List;

import com.enrikraymtz.springboot.model.Producto;

public interface ProductoService {
	
	Producto findById(Integer id_producto);
	
	Producto findByNombre(String nombre);
	
	void guardarProducto(Producto producto);
	
	void actualizarProducto(Producto producto);
	
	void eliminarProductoById(Integer id_producto);
	
	void eliminarTodosProductos();
	
	List<Producto> findAllProductos();
	
	boolean isProductoExist(Producto producto);
}
