package org.tncar.tncar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tncar.tncar.entity.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
