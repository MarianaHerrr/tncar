package org.tncar.tncar.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tncar.tncar.entity.Categoria;
import org.tncar.tncar.entity.DetalleOrden;
import org.tncar.tncar.entity.Orden;
import org.tncar.tncar.entity.Perfil;
import org.tncar.tncar.entity.Producto;
import org.tncar.tncar.entity.TipoServ;
import org.tncar.tncar.entity.Usuario;
import org.tncar.tncar.service.IntServiceCategorias;
import org.tncar.tncar.service.IntServiceDetalleOrdenes;
import org.tncar.tncar.service.IntServiceOrdenes;
import org.tncar.tncar.service.IntServiceProductos;
import org.tncar.tncar.service.IntServiceTiposServ;
import org.tncar.tncar.service.IntServiceUsuarios;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	private final Logger log= LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IntServiceTiposServ serviceTiposServ;
	
	@Autowired
	private IntServiceProductos serviceProductos;
	
	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	
	@Autowired
	private IntServiceCategorias serviceCategorias;
	
	@Autowired
	private IntServiceOrdenes serviceOrdenes;
	
	@Autowired
	private IntServiceDetalleOrdenes serviceDetelleOrdenes;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Para almacenar los detalles de la orden
	List<DetalleOrden> detalles= new ArrayList<DetalleOrden>();
	
	//Datos de la oreden
	Orden orden = new Orden();
	
	@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setFechaRegistro(LocalDate.now());
		usuario.setEstatus(1);
		String texto= usuario.getPassword();
		String encriptado= passwordEncoder.encode(texto);
		usuario.setPassword(encriptado);
		//usuario.setPassword("{noop}" + usuario.getPassword());
		Perfil per = new Perfil();
		per.setId(1);
		usuario.agregar(per);
		serviceUsuarios.agregar(usuario);
		System.out.println(usuario);
		return "redirect:/";
	}
	
	@GetMapping ("/logout")
	public String logout(HttpServletRequest request ) {
		SecurityContextLogoutHandler logoutHandler= new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "formRegistro";
	}
	
	@GetMapping("/login")
	public String login() {
		return "formLogin";
	}
	
	@GetMapping(value="/")
	public String mostrarIndex(Model model) {
		List<Producto> productos = serviceProductos.obtenerProductos();
		List<Categoria> categorias = serviceCategorias.obtenerCategorias();
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", categorias);
		return "home";
	
	}
	
	@GetMapping("/acerca")
	public String mostrarAcerca() {
		return "acerca";
	
	}
	@GetMapping("/contacto")
	public String mostrarContacto() {
		return "contacto";
	
	}
	

	@GetMapping("/catalogoproductos")
	public String mostrarProduct(Model model) {
		List<Producto> productos = serviceProductos.obtenerProductos();
		model.addAttribute("productos", productos);
		return "catalogoProductos";
	
	}
	@GetMapping("/catalogoservicios")
	public String mostrarServ(Model model) {
		List<TipoServ> tiposserv = serviceTiposServ.obtenerTiposServ();
		model.addAttribute("tiposserv", tiposserv);
		return "catalogoServicios";
	
	}
	///buscador
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		List<Categoria> categorias = serviceCategorias.obtenerCategorias();
		List<Producto> productos = serviceProductos.obtenerProductos().stream().filter(p -> p.getNombreP().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", categorias);
		return "home";
	}
	
	///Carrito
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer idProducto, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal=0;
		
		Optional <Producto> optionalProducto = serviceProductos.get(idProducto);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		
		producto=optionalProducto.get();
		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombreP());
		detalleOrden.setTotal(producto.getPrecio()*cantidad);
		detalleOrden.setProducto(producto);
		
		//validar que el producto no se añada dos veces
		
		Integer idProduc=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProduc);
		
		if(!ingresado) {
			detalles.add(detalleOrden);
		}
		
		
		sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "carrito";
	}
	
	//borrar producro del carrito 
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		
		//Lista nueva de productos
		List<DetalleOrden> ordenesNueva= new ArrayList<DetalleOrden>();
		
		for(DetalleOrden detalleOrden: detalles) {
			if(detalleOrden.getProducto().getId()!=id) {
				ordenesNueva.add(detalleOrden);
			}
		}
		//poner la nueva lista con los productos restantes
		detalles=ordenesNueva;
		
		double sumaTotal=0;
		sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		return "carrito";
	}
	
	@GetMapping("/getCart")
		public String getCart(Model model) {
			model.addAttribute("cart", detalles);
			model.addAttribute("orden", orden);
			
			return "carrito";
		}
	
	@GetMapping("/order")
	public String order(Model model) {
		Usuario usuario = serviceUsuarios.findById(6).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		
		return "resumenorden";
	}
	//Guardar orden
	@GetMapping("/saveOrder")
	public String saveOrder() {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(serviceOrdenes.generarNumeroOrden());
		
		//usuario
		Usuario usuario = serviceUsuarios.findById(6).get();
		
		orden.setUsuario(usuario);
		serviceOrdenes.save(orden);
		
		//guardar detalles
		for(DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			serviceDetelleOrdenes.save(dt);
		}
		///lIMPIAR LISTA Y ORDEN
		
		orden = new Orden();
		detalles.clear();
		

		return "redirect:/";
	}
	

}
