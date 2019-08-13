package com.inventario.tienda.service;

import java.util.List;

import com.inventario.tienda.model.Producto;

public interface ProductoService {

	List<Producto> FindAll();
	
	Producto FindProductoById(Long IdProducto);
	
	Producto FindProductoByName(String Nombre);
	
	void SaveProducto (Producto producto);
	
	void deleteProductoById (Long IdProducto);
		
}
