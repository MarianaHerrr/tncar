package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Perfil;
import org.tncar.tncar.repository.PerfilesRepository;
import org.tncar.tncar.service.IntServicePerfiles;
@Service
@Primary
public class PerfilesServiceJpa implements IntServicePerfiles {
	
	@Autowired
	private PerfilesRepository repoPerfiles;

	@Override
	public List<Perfil> obtnerPerfiles() {
		// TODO Auto-generated method stub
		return  repoPerfiles.findAll();
	}

	@Override
	public void agregar(Perfil perfil) {
		repoPerfiles.save(perfil);

	}

	@Override
	public Perfil buscarPorId(Integer idPerfil) {
		Optional<Perfil> optional = repoPerfiles.findById(idPerfil);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idPerfil) {
		repoPerfiles.deleteById(idPerfil);

	}

	@Override
	public long totalPerfiles() {
		return repoPerfiles.count();
	}

	@Override
	public Page<Perfil> buscarTodas(Pageable page) {
		return repoPerfiles.findAll(page);
	}

}
