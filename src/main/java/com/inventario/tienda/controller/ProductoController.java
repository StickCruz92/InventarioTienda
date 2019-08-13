package com.inventario.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.inventario.tienda.model.Producto;
import com.inventario.tienda.service.ProductoService;
import com.inventario.tienda.util.RespuestaGeneral;

@RestController
@RequestMapping(value = "/v1/productos")
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=Application/json")
	public @ResponseBody ResponseEntity<RespuestaGeneral> getAll () {
		
		try {
			List<Producto> productos = new ArrayList<>();
			
			productos = productoService.FindAll();
			if (productos.size() == 0) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", "No hay datos asociado a la consulta"),
						HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("200", "Operación exitosa", productos),
					HttpStatus.OK);
            
		} catch (Exception e) {
			
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("417", "Falta error: "+e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=Application/json")
	public @ResponseBody ResponseEntity<RespuestaGeneral> getProductoById (@PathVariable("id") Long IdProducto) {
		
		try {
			
			if (IdProducto <= 0) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", "El dato "+ IdProducto +" es Inavalido"),
						HttpStatus.CONFLICT);
			}
			
			Producto producto = productoService.FindProductoById(IdProducto);
			if (producto.getNombre() == null) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", "Falta el paramentro" + IdProducto),
						HttpStatus.CONFLICT);
			}
			
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("200", "Operación exitosa", producto),
					HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("417", "Falta error: "+e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=Application/json")
	public ResponseEntity<RespuestaGeneral> createProducto (@RequestBody Producto producto, UriComponentsBuilder uriComponentsBuilder) {
		
		try {
			if (producto.getNombre().equals(null) || producto.getNombre() == null || producto.getNombre().isEmpty()) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", ""),
						HttpStatus.CONFLICT);
			}
			
			if (producto.getCategoriaProducto().getIdCategoriaProducto().equals(null) || producto.getCategoriaProducto().getIdCategoriaProducto() == null || producto.getCategoriaProducto().getIdCategoriaProducto() <= 0) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", ""),
						HttpStatus.CONFLICT);
			}

			if (productoService.FindProductoByName(producto.getNombre()) != null) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", ""),
						HttpStatus.CONFLICT);
			}
			
			productoService.SaveProducto(producto);
			Producto currentProducto = productoService.FindProductoByName(producto.getNombre());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uriComponentsBuilder.path("/v1/productos/{id}")
					.buildAndExpand(currentProducto.getIdProducto()).toUri());
			
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("200", "Operación exitosa", headers),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("417", "Falta error: "+e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
		
		

	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PATCH, headers = "Accept=Application/json")
	public ResponseEntity<RespuestaGeneral> updateProducto (@PathVariable("id") Long IdProducto, @RequestBody Producto producto) {
		
		try {
			if (IdProducto == null || IdProducto.equals(null) || IdProducto <= 0) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", "Campos invalidos"),
						HttpStatus.CONFLICT);
			}
			
			Producto currentProducto = productoService.FindProductoById(IdProducto);
			if (currentProducto.equals(null) || currentProducto == null) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("204", "No hay datos en la consulta"),
						HttpStatus.NO_CONTENT);
			}
			
			currentProducto.setNombre(producto.getNombre());
			currentProducto.setEstado(producto.getEstado());
			currentProducto.setCategoriaProducto(producto.getCategoriaProducto());
			
			productoService.SaveProducto(currentProducto);
			
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("200", "Operación exitosa", currentProducto),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("417", "Falta error: "+e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, headers = "Accept=Application/json")
	public ResponseEntity<RespuestaGeneral> deleteProducto (@PathVariable("id") Long IdProducto) {
		
		try {
			if (IdProducto == null || IdProducto.equals(null) || IdProducto <= 0) {
				return new ResponseEntity<RespuestaGeneral>(
						new RespuestaGeneral("401", "Campo invalido"),HttpStatus.CONFLICT);
			}
			
			Producto producto = productoService.FindProductoById(IdProducto);
			if (producto != null) {
				productoService.deleteProductoById(IdProducto);
			}
			
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("200", "Operación exitosa"), HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<RespuestaGeneral>(
					new RespuestaGeneral("417", "Falta error: "+e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
		
	}
}
