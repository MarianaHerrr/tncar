package org.tncar.tncar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tncar.tncar.entity.Orden;

public interface OrdenesRepository extends JpaRepository<Orden, Integer> {

}
