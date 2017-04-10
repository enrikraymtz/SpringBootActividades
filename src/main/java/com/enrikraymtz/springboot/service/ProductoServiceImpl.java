package com.enrikraymtz.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enrikraymtz.springboot.model.Producto;
import com.enrikraymtz.springboot.repository.ProductoRepository;

@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public Producto findById(Integer id_producto){
		return productoRepository.findOne(id_producto);
	}
	
	public Producto findByNombre(String nombre){
		return productoRepository.findByNombre(nombre);
	}
	
	public void guardarProducto(Producto producto){
		productoRepository.save(producto);
	}
	
	public void actualizarProducto(Producto producto){
		guardarProducto(producto);
	}
	
	public void eliminarProductoById(Integer id_producto){
		productoRepository.delete(id_producto);
	}
	
	public void eliminarTodosProductos(){
		productoRepository.deleteAll();
	}
	
	public List<Producto> findAllProductos(){
		return productoRepository.findAll();
	}
	
	public boolean isProductoExist(Producto producto){
		return findByNombre(producto.getNombre()) != null;
	}

	
}
