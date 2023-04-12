package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Orden;

public interface IntServiceOrdenes {
	
	public List<Orden> obtenerOrdenes();
	public void guardar(Orden orden);
	public void eliminar(Integer idOrden);
	public Orden buscarPorId(Integer idOrden);
	public long totalOrdenes();
	Page<Orden>buscarTodas(Pageable page);
	Orden save (Orden orden);
	List<Orden> findAll();
	String generarNumeroOrden();

}
