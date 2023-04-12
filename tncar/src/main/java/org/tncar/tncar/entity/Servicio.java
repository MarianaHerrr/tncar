package org.tncar.tncar.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="Servicios")

public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double costo;
	private LocalDate fecha=LocalDate.now();
	
	@OneToOne
	@JoinColumn(name = "idTipoServ")
	private TipoServ tiposerv;
	
	@OneToOne
	@JoinColumn(name = "idAutomoviles")
	private Automovil automovil;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public TipoServ getTiposerv() {
		return tiposerv;
	}

	public void setTiposerv(TipoServ tiposerv) {
		this.tiposerv = tiposerv;
	}

	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", costo=" + costo
				+ ", fecha=" + fecha + ", tiposerv=" + tiposerv + ", automovil=" + automovil + "]";
	}
	
	
	

}
