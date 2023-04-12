package org.tncar.tncar.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos")

public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombreP;
	private Double precio;
	private Double descuento;
	private Integer stock;
	private Integer existencia;
	private String descripcion;
	private String marca;
	private String imagen;
	
	@OneToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	
	
	public Producto() {	 
	}
	
	public Producto(Integer id, String nombreP, Double precio, Double descuento, Integer stock, Integer existencia,
			String descripcion, String marca, String imagen, Categoria categoria, Usuario usuario) {
		super();
		this.id = id;
		this.nombreP = nombreP;
		this.precio = precio;
		this.descuento = descuento;
		this.stock = stock;
		this.existencia = existencia;
		this.descripcion = descripcion;
		this.marca = marca;
		this.imagen = imagen;
		this.categoria = categoria;
		this.usuario = usuario;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombreP() {
		return nombreP;
	}
	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getExistencia() {
		return existencia;
	}
	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
		
		
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombreP=" + nombreP + ", precio=" + precio + ", descuento=" + descuento
				+ ", stock=" + stock + ", existencia=" + existencia + ", descripcion=" + descripcion + ", marca="
				+ marca + ", imagen=" + imagen + ", categoria=" + categoria + ", usuario=" + usuario + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

	
}
