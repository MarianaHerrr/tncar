package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Producto;
import org.tncar.tncar.repository.ProductosRepository;
import org.tncar.tncar.service.IntServiceProductos;

@Service
@Primary
public class ProductosServiceJpa implements IntServiceProductos {
	
	@Autowired
	private ProductosRepository repoProductos;

	@Override
	public List<Producto> obtenerProductos() {
		return repoProductos.findAll() ;
	}

	@Override
	public void guardar(Producto producto) {
		repoProductos.save(producto);

	}

	@Override
	public void eliminar(Integer idProducto) {
		repoProductos.deleteById(idProducto);

	}

	@Override
	public Producto buscarPorId(Integer idProducto) {
		Optional<Producto> optional = repoProductos.findById(idProducto);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalProductos() {
		return (int)repoProductos.count() ;
	}

	@Override
	public Page<Producto> buscarTodas(Pageable page) {
		return null;
	}

}
