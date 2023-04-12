package org.tncar.tncar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tncar.tncar.entity.Usuario;

public interface IntServiceUsuarios {
	public List<Usuario> obtnerUsuarios();
	public void agregar(Usuario usuario);
	public Usuario buscarPorId(Integer idUsuario);
	public void eliminar(Integer idUsuario);
	public long totalUsuarios();
	Page<Usuario>buscarTodas(Pageable page);
	Optional<Usuario>findById(Integer id);

}
