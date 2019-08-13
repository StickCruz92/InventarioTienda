package com.inventario.tienda.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventario.tienda.dao.ProductoDao;
import com.inventario.tienda.model.Producto;

@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDao productoDao;

	@Override
	public List<Producto> FindAll() {
		// TODO Auto-generated method stub
		return productoDao.findAll();
	}

	@Override
	public Producto FindProductoById(Long IdProducto) {
		// TODO Auto-generated method stub
		return (Producto) productoDao.findById(IdProducto).get();
	}

	@Override
	public void SaveProducto(Producto producto) {
		// TODO Auto-generated method stub
		productoDao.save(producto);
		
	}

	@Override
	public Producto FindProductoByName(String Nombre) {
		// TODO Auto-generated method stub
		return productoDao.FindProductoByName(Nombre);
	}

	@Override
	public void deleteProductoById(Long IdProducto) {
		// TODO Auto-generated method stub
		Producto producto = FindProductoById(IdProducto);
		if (producto != null) {
			productoDao.delete(producto);
			productoDao.deleteById(IdProducto);
		}
	}

}
