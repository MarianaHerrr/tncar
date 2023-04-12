package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Automovil;


public interface IntServiceAutomoviles {
	
	public List<Automovil> obtenerAutomoviles();
	public void guardar(Automovil automovil);
	public void eliminar(Integer idAutomovil);
	public Automovil buscarPorId(Integer idAutomovil);
	public long totalAutomoviles();
	Page<Automovil>buscarTodas(Pageable page);

}
