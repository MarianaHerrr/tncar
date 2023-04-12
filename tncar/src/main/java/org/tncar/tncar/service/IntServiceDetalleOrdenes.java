package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.DetalleOrden;

public interface IntServiceDetalleOrdenes {
	
	public List<DetalleOrden> obtenerDetalleOrdenes();
	public void guardar(DetalleOrden detalleorden);
	public void eliminar(Integer idDetalleOrden);
	public DetalleOrden buscarPorId(Integer idDetalleOrden);
	public long totalDetalleOrdenes();
	Page<DetalleOrden>buscarTodas(Pageable page);
	
	DetalleOrden save (DetalleOrden detalleOrden);

}
