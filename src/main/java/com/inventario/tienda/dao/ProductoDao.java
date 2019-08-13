package com.inventario.tienda.dao;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.inventario.tienda.model.Producto;

@Repository
@Transactional
public interface ProductoDao extends JpaRepository<Producto, Long> {
	
	@Query("select producto from Producto producto where producto.estado = 1")
    List<Producto> findAllProducto();
    
	@Query("select producto from Producto producto where producto.nombre = ?1")
    Producto FindProductoByName(String nombre);
	
	
}
