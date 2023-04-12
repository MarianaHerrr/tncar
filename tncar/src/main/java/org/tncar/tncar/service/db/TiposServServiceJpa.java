package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.TipoServ;
import org.tncar.tncar.repository.TiposServRepository;
import org.tncar.tncar.service.IntServiceTiposServ;

@Service
@Primary
public class TiposServServiceJpa implements IntServiceTiposServ {
	
	@Autowired 
	private TiposServRepository repoTiposServ;

	@Override
	public List<TipoServ> obtenerTiposServ() {
		// TODO Auto-generated method stub
		return repoTiposServ.findAll();
	}

	@Override
	public void guardar(TipoServ tiposerv) {
		// TODO Auto-generated method stub
		repoTiposServ.save(tiposerv);

	}

	@Override
	public void eliminar(Integer idTipoServ) {
		// TODO Auto-generated method stub
		repoTiposServ.deleteById(idTipoServ);

	}

	@Override
	public TipoServ buscarPorId(Integer idTipoServ) {
		Optional<TipoServ> optional = repoTiposServ.findById(idTipoServ);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalTipoServ() {
		// TODO Auto-generated method stub
		return (int)repoTiposServ.count() ;
	}

	@Override
	public Page<TipoServ> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoTiposServ.findAll(page);
	}

}
