package org.tncar.tncar.controller;

//import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tncar.tncar.entity.Automovil;
import org.tncar.tncar.service.IntServiceAutomoviles;

@RequestMapping("/automoviles")
@Controller
public class AutomovilesController {

	@Autowired
	private IntServiceAutomoviles serviceAutomoviles;
	
	@GetMapping("/buscar")
	public String modificarCategoria(@RequestParam("id")int idAutomovil,Model model) {
		Automovil automovil=serviceAutomoviles.buscarPorId(idAutomovil);
		model.addAttribute("automovil", automovil);
		return "automoviles/formAutomovil";
		
	}
	
	@GetMapping("/eliminar")
	public String eliminarAuto(@RequestParam("id") int idAutomovil, RedirectAttributes model) {
		serviceAutomoviles.eliminar(idAutomovil);
		model.addFlashAttribute("msg", "Auto Eliminado");
		return "redirect:/automoviles/indexPaginado";
		
	}
	
	@PostMapping("/guardar")
	public String guardar(Automovil automovil, RedirectAttributes model) {
		serviceAutomoviles.guardar(automovil);
		
		return "redirect:/automoviles/indexPaginado";
	}
	
	@GetMapping("/nueva")
	public String nuevaAuto(Automovil automovil) {
		return"automoviles/formAutomovil";
	}
	
//	@GetMapping("/index")
//	public String mostrarIndex(Model model) {
//		List<Automovil> automoviles = serviceAutomoviles.obtenerAutomoviles();
//		model.addAttribute("automoviles", automoviles);
//		model.addAttribute("total", serviceAutomoviles.totalAutomoviles());
//		return"automoviles/listaAutomoviles";
//	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Automovil> lista = serviceAutomoviles.buscarTodas(page); 
	model.addAttribute("automoviles", lista);
	model.addAttribute("total", serviceAutomoviles.totalAutomoviles());
	return "automoviles/listaAutomoviles";
	}
}
