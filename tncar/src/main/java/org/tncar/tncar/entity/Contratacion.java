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
@Table(name="Contrataciones")
public class Contratacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String modelo;
	private LocalDate fechaIngreso=LocalDate.now();
	private String adicional;

	@OneToOne
	@JoinColumn(name = "idTipoServ")
	private TipoServ tiposerv;
	
	@OneToOne
	@JoinColumn(name = "idAuto")
	private Automovil automovil;
	
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
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


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Contratacion [id=" + id + ", modelo=" + modelo + ", fechaIngreso=" + fechaIngreso + ", adicional="
				+ adicional + ", tiposerv=" + tiposerv + ", automovil=" + automovil + ", usuario=" + usuario + "]";
	}

	
	

}
