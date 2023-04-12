package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Automovil;
import org.tncar.tncar.repository.AutomovilesRepository;
import org.tncar.tncar.service.IntServiceAutomoviles;
@Service
@Primary
public class AutomovilesServiceJpa implements IntServiceAutomoviles {
	
	@Autowired
	private AutomovilesRepository repoAutomoviles;

	@Override
	public List<Automovil> obtenerAutomoviles() {
		return repoAutomoviles.findAll();
	}

	@Override
	public void guardar(Automovil automovil) {
		repoAutomoviles.save(automovil);

	}

	@Override
	public void eliminar(Integer idAutomovil) {
		repoAutomoviles.deleteById(idAutomovil);

	}

	@Override
	public Automovil buscarPorId(Integer idAutomovil) {
		Optional<Automovil> optional = repoAutomoviles.findById(idAutomovil);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalAutomoviles() {
		// TODO Auto-generated method stub
		return (int)repoAutomoviles.count();
	}

	@Override
	public Page<Automovil> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoAutomoviles.findAll(page);
	}

}
