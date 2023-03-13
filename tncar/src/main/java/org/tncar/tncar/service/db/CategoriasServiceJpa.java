package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Categoria;
import org.tncar.tncar.repository.CategoriasRepository;
import org.tncar.tncar.service.IntServiceCategorias;

@Service
@Primary
public class CategoriasServiceJpa implements IntServiceCategorias {

	@Autowired
	private CategoriasRepository repoCategorias;
	@Override
	public List<Categoria> obtenerCategorias() {
		return repoCategorias.findAll() ;
		
	}

	@Override
	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);

	}

	@Override
	public void eliminar(Integer idCategoria) {
		repoCategorias.deleteById(idCategoria);

	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalCategorias() {
		// TODO Auto-generated method stub
		return (int)repoCategorias.count() ;
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
