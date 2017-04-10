package com.enrikraymtz.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.enrikraymtz.springboot.model.Producto;
import com.enrikraymtz.springboot.service.ProductoService;
import com.enrikraymtz.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class ProductoRestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(ProductoRestApiController.class);
	
	@Autowired
	ProductoService productoService;
	
	//  Recuperar todos los producto
	@RequestMapping(value = "/producto/", method = RequestMethod.GET )
	public ResponseEntity<List<Producto>> listAllProductos(){
		List<Producto> productos = productoService.findAllProductos();
		if(productos.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	//	Recuperar un producto
	@RequestMapping(value = "/producto/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getProducto(@PathVariable("id") Integer id_producto){
		logger.info("Recuperar producto con id {}", id_producto);
		Producto producto = productoService.findById(id_producto);
		if (producto == null){
			logger.error("Producto con id {} no encontrado");
			return new ResponseEntity(new CustomErrorType("Producto con id "+ id_producto
					+ " no encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	// crear un producto
	@RequestMapping(value="/producto/", method=RequestMethod.POST)
	public ResponseEntity<?> crearProducto(@RequestBody Producto producto, UriComponentsBuilder ucBuilder){
		logger.info("Creando producto: {}", producto);
		if(productoService.isProductoExist(producto)){
			logger.error("Incapaz de crear. Ya existe un producto con el nombre {}", producto.getNombre());
			return new ResponseEntity(new CustomErrorType("Imposible de crear. A el producto con nombre" +
					producto.getNombre() + " ya existe."), HttpStatus.CONFLICT);
		}
		productoService.guardarProducto(producto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/producto/{id}").buildAndExpand(producto.getId_producto()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// Actualizar a producto
	@RequestMapping(value="/producto/{id}", method= RequestMethod.PUT)
	public ResponseEntity<?> actualizarProducto(@PathVariable("id") Integer id_producto, @RequestBody Producto producto){
		logger.info("Actualizando a usuario con id {}", id_producto);
		
		Producto currentProducto = productoService.findById(id_producto);
		
		if(currentProducto == null){
			logger.error("Imposible actualizar. Producto con id {} no encontrado.", id_producto);
			return new ResponseEntity<>(new CustomErrorType("Imposible actualizar. Producto con id " +
						id_producto + " no encontrado."), HttpStatus.NOT_FOUND);			
		}
		
		currentProducto.setNombre(producto.getNombre());
		currentProducto.setCantidad(producto.getCantidad());
		currentProducto.setPrecio_unitario(producto.getPrecio_unitario());
		currentProducto.setPrecio_iva(producto.getPrecio_iva());
		
		productoService.actualizarProducto(currentProducto);
		return new ResponseEntity<Producto>(currentProducto, HttpStatus.OK);
	}
	
	//Eliminar producto por id
	@RequestMapping(value="/producto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Integer id_producto ){
		logger.info("Recuperando y eliminando producto con id {}", id_producto);
		
		Producto producto = productoService.findById(id_producto);
		if(producto == null){
			logger.info("Imposible eliminar. Producto con id {} no encontrado", id_producto);
			return new ResponseEntity(new CustomErrorType("Imposible eliminar. Producto con id "+ id_producto +" no encontrado"),
					HttpStatus.NOT_FOUND);
		}
		productoService.eliminarProductoById(id_producto);
		return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
	}
	
	//Eliminando a todos los productos
	@RequestMapping(value="/producto/", method = RequestMethod.DELETE)
	public ResponseEntity<Producto> eliminarTodosProductos(){
		logger.info("Eliminando a todos los productos");
		productoService.eliminarTodosProductos();
		return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
