package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.Usuario;
import org.tncar.tncar.repository.UsuariosRepository;
import org.tncar.tncar.service.IntServiceUsuarios;
@Service
@Primary
public class UsuariosServiceJpa implements IntServiceUsuarios {
	
	@Autowired
	private UsuariosRepository repoUsuarios;

	@Override
	public List<Usuario> obtnerUsuarios() {
		return repoUsuarios.findAll();
	}

	@Override
	public void agregar(Usuario usuario) {
		repoUsuarios.save(usuario);

	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		Optional<Usuario> optional = repoUsuarios.findById(idUsuario);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idUsuario) {
		repoUsuarios.deleteById(idUsuario);

	}

	@Override
	public long totalUsuarios() {
		return repoUsuarios.count();
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		return repoUsuarios.findAll(page);
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		// TODO Auto-generated method stub
		return repoUsuarios.findById(id);
	}

	

}
