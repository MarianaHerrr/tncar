package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Contratacion;
import org.tncar.tncar.repository.ContratacionesRepository;
import org.tncar.tncar.service.IntServiceContrataciones;

@Service
@Primary
public class ContratacionesServiceJpa implements IntServiceContrataciones {
	@Autowired
	private ContratacionesRepository repoContrataciones;

	@Override
	public List<Contratacion> obtenerContrataciones() {
		// TODO Auto-generated method stub
		return repoContrataciones.findAll();
	}

	@Override
	public void guardar(Contratacion contratacion) {
		repoContrataciones.save(contratacion);

	}

	@Override
	public void eliminar(Integer idContratacion) {
		repoContrataciones.deleteById(idContratacion);

	}

	@Override
	public Contratacion buscarPorId(Integer idContratacion) {
		Optional<Contratacion> optional = repoContrataciones.findById(idContratacion);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

	}

	@Override
	public long totalContrataciones() {
		// TODO Auto-generated method stub
		return (int)repoContrataciones.count();
	}

	@Override
	public Page<Contratacion> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
				return repoContrataciones.findAll(page);
	}

}
