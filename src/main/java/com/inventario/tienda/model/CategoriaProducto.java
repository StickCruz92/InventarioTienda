package com.inventario.tienda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="categoria_producto")
public class CategoriaProducto implements Serializable {
	
	@Id
	@Column(name="id_categoria_producto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCategoriaProducto;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="estado")
	private int estado;
	
	@OneToMany(mappedBy = "categoriaProducto", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Producto> productos;
	
	public CategoriaProducto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoriaProducto(String nombre, int estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		
	}
	
	public CategoriaProducto(String nombre, int estado, List<Producto> productos) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.productos = productos;
	}
	
	public Long getIdCategoriaProducto() {
		return idCategoriaProducto;
	}
	public void setIdCategoriaProducto(Long idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
}
