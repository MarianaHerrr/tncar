package org.tncar.tncar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tncar.tncar.entity.DetalleOrden;
import org.tncar.tncar.repository.DetalleOrdenesRepository;
import org.tncar.tncar.service.IntServiceDetalleOrdenes;
@Service
@Primary
public class DetalleOrdenesServiceJpa implements IntServiceDetalleOrdenes {
	
	@Autowired
	private DetalleOrdenesRepository repoDetalleOrdenes;

	@Override
	public List<DetalleOrden> obtenerDetalleOrdenes() {
		return repoDetalleOrdenes.findAll();
	}

	@Override
	public void guardar(DetalleOrden detalleorden) {
		repoDetalleOrdenes.save(detalleorden);

	}

	@Override
	public void eliminar(Integer idDetalleOrden) {
		repoDetalleOrdenes.deleteById(idDetalleOrden);

	}

	@Override
	public DetalleOrden buscarPorId(Integer idDetalleOrden) {
		Optional<DetalleOrden> optional = repoDetalleOrdenes.findById(idDetalleOrden);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalDetalleOrdenes() {
		return (int)repoDetalleOrdenes.count();
	}

	@Override
	public Page<DetalleOrden> buscarTodas(Pageable page) {
		return repoDetalleOrdenes.findAll(page);
	}

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		
		return repoDetalleOrdenes.save(detalleOrden);
	}

}
