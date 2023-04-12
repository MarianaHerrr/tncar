package org.tncar.tncar.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name= "Ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private double total;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @OneToOne(mappedBy = "orden")
    private DetalleOrden detalle;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaRecibida() {
		return fechaRecibida;
	}
	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public DetalleOrden getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleOrden detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + ", usuario=" + usuario + ", detalle=" + detalle + "]";
	}
	
	
	
    
    
}
