package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.tncar.tncar.entity.TipoServ;

public interface IntServiceTiposServ {
		public List<TipoServ> obtenerTiposServ();
		public void guardar(TipoServ tiposerv);
		public void eliminar(Integer idTipoServ);
		public TipoServ buscarPorId(Integer idTipoServ);
		public long totalTipoServ();
		Page<TipoServ>buscarTodas(Pageable page);
	}


