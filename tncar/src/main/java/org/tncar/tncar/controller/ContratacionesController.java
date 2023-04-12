package org.tncar.tncar.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import org.tncar.tncar.entity.Contratacion;
import org.tncar.tncar.entity.TipoServ;
import org.tncar.tncar.service.IntServiceAutomoviles;
import org.tncar.tncar.service.IntServiceContrataciones;
import org.tncar.tncar.service.IntServiceTiposServ;

@RequestMapping("/contrataciones")
@Controller
public class ContratacionesController {

	
	@Autowired
	private IntServiceContrataciones serviceContrataciones;
	
	@Autowired
	private IntServiceTiposServ serviceTiposServ;
	
	@Autowired
	private IntServiceAutomoviles serviceAutomoviles;
	
	@PostMapping("/guardar")
	public String guardar(Contratacion contratacion, RedirectAttributes model) {
		serviceContrataciones.guardar(contratacion);
		List<Contratacion> contrataciones = serviceContrataciones.obtenerContrataciones();
		model.addAttribute("contratacion", contrataciones);
		return "redirect:/contrataciones/comprobante";
	}
	
	
	@GetMapping("/comprobante")
	public String comprobante(Contratacion contratacion, RedirectAttributes model) {
		model.addAttribute("contrataciones", contratacion);
		return "contrataciones/comprobante";
	}
	
	@GetMapping("/eliminar")
	public String eliminarProducto(@RequestParam("id") int idContratacion, RedirectAttributes model) {
		serviceContrataciones.eliminar(idContratacion);
		model.addFlashAttribute("msg","Contratacion Eliminada");
		return "redirect:/contrataciones/indexPaginado";
		
	}
	
	
	
	@GetMapping("/cancelar")
	public String cancelar(Model model) {
		List<TipoServ> tiposserv = serviceTiposServ.obtenerTiposServ();
		model.addAttribute("tiposserv", tiposserv);
		return "catalogoServicios";
	
	}
	
	
	
	@GetMapping("/nueva")
	public String nuevaContratacion(Contratacion contratacion, Model model) {
		model.addAttribute("tiposserv", serviceTiposServ.obtenerTiposServ());
		model.addAttribute("automoviles", serviceAutomoviles.obtenerAutomoviles());
		return"contrataciones/formContratacion";

	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Contratacion> lista = serviceContrataciones.buscarTodas(page); 
	model.addAttribute("contrataciones", lista);
	model.addAttribute("total", serviceContrataciones.totalContrataciones());
	return "contrataciones/listaContrataciones";
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


