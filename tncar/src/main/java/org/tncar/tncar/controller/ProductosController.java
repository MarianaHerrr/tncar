package org.tncar.tncar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tncar.tncar.entity.Producto;
import org.tncar.tncar.service.IntServiceCategorias;
import org.tncar.tncar.service.IntServiceProductos;
import org.tncar.tncar.util.Utileria;

@RequestMapping("/productos")
@Controller
public class ProductosController {

	@Autowired
	private IntServiceProductos serviceProductos;
	
	@Autowired
	private IntServiceCategorias serviceCategorias;
	
	
	@GetMapping("/buscar")
	public String buscar (@RequestParam("id") int idProducto,Model model) {
		Producto producto= serviceProductos.buscarPorId(idProducto);
		model.addAttribute("categorias", serviceCategorias.obtenerCategorias());
		model.addAttribute("producto", producto);
		return "productos/formProducto";
		
	}
	
	
	@GetMapping("/eliminar")
	public String eliminarLibro(@RequestParam("id") int idProducto, RedirectAttributes model) {
		serviceProductos.eliminar(idProducto);
		model.addFlashAttribute("msg", "Producto Eliminado");
		return "redirect:/productos/index";
		
	}
	
	@PostMapping("/guardar")
	public String  guardar(Producto producto,BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(producto);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("productos",serviceProductos.obtenerProductos());
			return"productos/formProductos";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/biblioteca/img-libros/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			producto.setImagen(nombreImagen); 
			}

		}
		if(producto.getId()==null)
			model.addFlashAttribute("msg","Producto agregado");
		else model.addFlashAttribute("msg","Producto modificado");
		serviceProductos.guardar(producto);
		return "redirect:/productos/index";
		
		
	}
	
	
	
	@GetMapping("/nueva")
	public String nuevaCategoria(Producto producto, Model model) {
		model.addAttribute("categorias", serviceCategorias.obtenerCategorias());
		return"productos/formProducto";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Producto> productos = serviceProductos.obtenerProductos();
		model.addAttribute("productos", productos);
		model.addAttribute("total", serviceProductos.totalProductos());
		return"productos/listaProductos";
	}
}
