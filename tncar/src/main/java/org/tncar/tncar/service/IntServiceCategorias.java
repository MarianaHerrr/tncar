package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Categoria;


public interface IntServiceCategorias {
	public List<Categoria> obtenerCategorias();
	public void guardar(Categoria categoria);
	public void eliminar(Integer idCategoria);
	public Categoria buscarPorId(Integer idCategoria);
	public long totalCategorias();
	Page<Categoria>buscarTodas(Pageable page);

}
