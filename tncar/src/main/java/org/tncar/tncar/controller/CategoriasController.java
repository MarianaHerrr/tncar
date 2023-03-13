package org.tncar.tncar.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tncar.tncar.entity.Categoria;
import org.tncar.tncar.service.IntServiceCategorias;
@RequestMapping("/categorias")
@Controller
public class CategoriasController {
	
	@Autowired
	private IntServiceCategorias serviceCategorias;
	
	@GetMapping("/buscar")
	public String modificarCategoria(@RequestParam("id")int idCategoria,Model model) {
		Categoria categoria=serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
		
	}
	
	@GetMapping("/eliminar")
	public String eliminarCategoria(@RequestParam("id") int idCategoria, RedirectAttributes model) {
		serviceCategorias.eliminar(idCategoria);
		model.addFlashAttribute("msg", "Categoria Eliminada");
		return "redirect:/categorias/index";
		
	}
	
	@PostMapping("/guardar")
	public String guardar(Categoria categoria, RedirectAttributes model) {
		serviceCategorias.guardar(categoria);
		
		return "redirect:/categorias/index";
	}
	
	
	@GetMapping("/nueva")
	public String nuevaCategorias(Categoria categoria) {
		return"categorias/formCategoria";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> categorias = serviceCategorias.obtenerCategorias();
		model.addAttribute("categorias", categorias);
		model.addAttribute("total", serviceCategorias.totalCategorias());
		return"categorias/listaCategorias";
	}

}
