package org.tncar.tncar.service;

import java.util.List;
import java.util.Optional;

import org.tncar.tncar.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceProductos {
	public List<Producto> obtenerProductos();
	public void guardar(Producto producto);
	public void eliminar(Integer idProducto);
	public Producto buscarPorId(Integer idProducto);
	public long totalProductos();
	Page<Producto>buscarTodas(Pageable page);
	public Optional<Producto> get(Integer id);

}
