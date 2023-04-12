package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Contratacion;

public interface IntServiceContrataciones {
	public List<Contratacion> obtenerContrataciones();
	public void guardar(Contratacion contratacion);
	public void eliminar(Integer idContratacion);
	public Contratacion buscarPorId(Integer idContratacion);
	public long totalContrataciones();
	Page<Contratacion>buscarTodas(Pageable page);

}
