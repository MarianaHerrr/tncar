package org.tncar.tncar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tncar.tncar.entity.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer> {

}
