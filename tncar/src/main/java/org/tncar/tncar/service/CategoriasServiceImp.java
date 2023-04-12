package org.tncar.tncar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Categoria;


public class CategoriasServiceImp implements IntServiceCategorias {
	
	private  List<Categoria> categorias =null;

	@Override
	public List<Categoria> obtenerCategorias() {
		// TODO Auto-generated method stub
		return categorias;
	}

	@Override
	public void guardar(Categoria categoria) {
		categorias.add(categoria);

	}

	@Override
	public void eliminar(Integer idCategoria) {
		categorias.remove(buscarPorId(idCategoria));

	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria cat : categorias) {
			if(cat.getId().compareTo(idCategoria) == 0) {
				return cat;
			}
		}
		return null;
		
	}

	@Override
	public long totalCategorias() {
		// TODO Auto-generated method stub
		return categorias.size();
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
