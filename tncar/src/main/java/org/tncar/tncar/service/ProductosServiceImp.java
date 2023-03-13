package org.tncar.tncar.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Producto;

public class ProductosServiceImp implements IntServiceProductos {
	
	private  List<Producto> productos =null;

	@Override
	public List<Producto> obtenerProductos() {
		return productos;
	}

	@Override
	public void guardar(Producto producto) {
		productos.add(producto);

	}

	@Override
	public void eliminar(Integer idProducto) {
		productos.remove(buscarPorId(idProducto));

	}

	@Override
	public Producto buscarPorId(Integer idProducto) {
		for(Producto prod : productos) {
			if(prod.getId().compareTo(idProducto) == 0) {
				return prod;
			}
		}
		return null;
		
	}
	

	@Override
	public long totalProductos() {
		return productos.size();
	}

	@Override
	public Page<Producto> buscarTodas(Pageable page) {
		return null;
	}

}
