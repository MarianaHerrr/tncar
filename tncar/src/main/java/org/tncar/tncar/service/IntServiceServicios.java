package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Servicio;

public interface IntServiceServicios {
	public List<Servicio> obtenerServicios();
	public void guardar(Servicio servicio);
	public void eliminar(Integer idServicio);
	public Servicio buscarPorId(Integer idServicio);
	public long totalServicios();
	Page<Servicio>buscarTodas(Pageable page);

}
