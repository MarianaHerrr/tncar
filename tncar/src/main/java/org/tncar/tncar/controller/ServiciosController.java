package org.tncar.tncar.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tncar.tncar.entity.Servicio;
import org.tncar.tncar.service.IntServiceAutomoviles;
import org.tncar.tncar.service.IntServiceServicios;
import org.tncar.tncar.service.IntServiceTiposServ;

@RequestMapping("/servicios")
@Controller
public class ServiciosController {

	@Autowired
	private IntServiceServicios serviceServicios;
	
	@Autowired
	private IntServiceTiposServ serviceTiposServ;
	
	@Autowired
	private IntServiceAutomoviles serviceAutomoviles;
	
	@PostMapping("/guardar")
	public String guardar(Servicio servicio, RedirectAttributes model) {
		serviceServicios.guardar(servicio);
		
		return "redirect:/servicios/indexPaginado";
	}
	
	@GetMapping("/eliminar")
	public String eliminarServ(@RequestParam("id") int idServicio, RedirectAttributes model) {
		serviceServicios.eliminar(idServicio);
		model.addFlashAttribute("msg", "Servicio Eliminado");
		return "redirect:/servicios/indexPaginado";
		
	}
	
	@GetMapping("/nueva")
	public String nuevaServicio(Servicio servicio, Model model) {
		model.addAttribute("tiposserv", serviceTiposServ.obtenerTiposServ());
		model.addAttribute("automoviles", serviceAutomoviles.obtenerAutomoviles());
		return"servicios/formServicio";
	}
	
//	@GetMapping("/index")
//	public String mostrarIndex(Model model) {
//		List<Servicio> servicios = serviceServicios.obtenerServicios();
//		model.addAttribute("servicios", servicios);
//		model.addAttribute("total", serviceServicios.totalServicios());
//		return"servicios/listaServicios";
//	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Servicio> lista = serviceServicios.buscarTodas(page); 
	model.addAttribute("servicios", lista);
	model.addAttribute("total", serviceServicios.totalServicios());
	return "servicios/listaServicios";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }  
    
      });
  }
	
}
