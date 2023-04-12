package org.tncar.tncar.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity
@Table(name= "Usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String email;
	private String username;
	private String password;
	private Integer estatus;
	private LocalDate fechaRegistro;
	private Integer edad;
	private String sexo;
	
	@ManyToMany (fetch=FetchType.EAGER)
	@JoinTable (name="UsuarioPerfil",
	joinColumns=@JoinColumn (name="idUsuario"),
	inverseJoinColumns=@JoinColumn(name="idPerfil"))
	private List<Perfil> perfiles;
	
	@OneToMany(mappedBy = "usuario")
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;

	public Integer getId() {
		return id;
	}
	
	
	
	
	

	public Usuario() {
		
	}






	public Usuario(Integer id, String nombre, String email, String username, String password, Integer estatus,
			LocalDate fechaRegistro, Integer edad, String sexo, List<Perfil> perfiles, List<Producto> productos,
			List<Orden> ordenes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.username = username;
		this.password = password;
		this.estatus = estatus;
		this.fechaRegistro = fechaRegistro;
		this.edad = edad;
		this.sexo = sexo;
		this.perfiles = perfiles;
		this.productos = productos;
		this.ordenes = ordenes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	

	public List<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	

	

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + ", edad="
				+ edad + ", sexo=" + sexo + ", perfiles=" + perfiles + ", productos=" + productos + ", ordenes="
				+ ordenes + "]";
	}






	public void agregar(Perfil tempPerfil) {
		if(perfiles == null) {
			perfiles=new LinkedList<Perfil>();
		}
		perfiles.add(tempPerfil);
	}
	

}
