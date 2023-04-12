package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Servicio;
import org.tncar.tncar.repository.ServiciosRepository;
import org.tncar.tncar.service.IntServiceServicios;
@Service
@Primary
public class ServiciosServiceJpa implements IntServiceServicios {
	
	@Autowired
	private ServiciosRepository repoServicios;

	@Override
	public List<Servicio> obtenerServicios() {
		// TODO Auto-generated method stub
		return repoServicios.findAll();
	}

	@Override
	public void guardar(Servicio servicio) {
		// TODO Auto-generated method stub
		repoServicios.save(servicio);

	}

	@Override
	public void eliminar(Integer idServicio) {
		repoServicios.deleteById(idServicio);

	}

	@Override
	public Servicio buscarPorId(Integer idServicio) {
		Optional<Servicio> optional = repoServicios.findById(idServicio);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalServicios() {
		// TODO Auto-generated method stub
		return (int)repoServicios.count();
	}

	@Override
	public Page<Servicio> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoServicios.findAll(page);
	}

}
