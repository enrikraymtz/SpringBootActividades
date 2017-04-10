package com.enrikraymtz.springboot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PRODUCTO")
public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id_producto;
	
	@NotEmpty
	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	
	@Column(name="CANTIDAD", nullable=false)
	private Integer cantidad;
	

	@Column(name="PRECIO_UNITARIO", nullable=false)
	private Integer precio_unitario;

	@Column(name="PRECIO_IVA", nullable=false)
	private Integer precio_iva;

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(Integer precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public Integer getPrecio_iva() {
		return precio_iva;
	}

	public void setPrecio_iva(Integer precio_iva) {
		this.precio_iva = precio_iva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((id_producto == null) ? 0 : id_producto.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio_iva == null) ? 0 : precio_iva.hashCode());
		result = prime * result + ((precio_unitario == null) ? 0 : precio_unitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (id_producto == null) {
			if (other.id_producto != null)
				return false;
		} else if (!id_producto.equals(other.id_producto))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio_iva == null) {
			if (other.precio_iva != null)
				return false;
		} else if (!precio_iva.equals(other.precio_iva))
			return false;
		if (precio_unitario == null) {
			if (other.precio_unitario != null)
				return false;
		} else if (!precio_unitario.equals(other.precio_unitario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", cantidad=" + cantidad
				+ ", precio_unitario=" + precio_unitario + ", precio_iva=" + precio_iva + "]";
	}
	
	
}
