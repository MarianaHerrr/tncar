package org.tncar.tncar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.tncar.tncar.entity.TipoServ;
import org.tncar.tncar.service.IntServiceTiposServ;
import org.tncar.tncar.util.Utileria;


@RequestMapping("/tiposserv")
@Controller
public class TiposServController {
	
	@Value("${tncar.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IntServiceTiposServ serviceTiposServ;
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idTipoServ, Model model) {
		TipoServ tiposerv =serviceTiposServ.buscarPorId(idTipoServ);
		model.addAttribute("tiposerv", tiposerv);
		return "tiposserv/detalle";
	}
	
	@GetMapping("/buscar")
	public String modificarServicio(@RequestParam("id")int idTipoServ,Model model) {
		TipoServ tiposerv =serviceTiposServ.buscarPorId(idTipoServ);
		model.addAttribute("tiposerv", tiposerv);
		return "tiposserv/formTipos";
		
	}
	
	@GetMapping("/eliminar")
	public String eliminarTipoServ(@RequestParam("id") int idTipoServ, RedirectAttributes model) {
		serviceTiposServ.eliminar(idTipoServ);
		model.addFlashAttribute("msg", "Servicio Eliminado");
		return "redirect:/tiposserv/indexPaginado";
		
	}
	
	/*@PostMapping("/guardar")
	public String guardar(TipoServ tiposerv, RedirectAttributes model) {
		serviceTiposServ.guardar(tiposerv);
		
		return "redirect:/tiposserv/index";
	}*/
	
	@PostMapping("/guardar")
	public String guardar(TipoServ tiposerv, BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(tiposerv);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("productos",serviceTiposServ.obtenerTiposServ());
			return"tiposserv/formTipos";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/biblioteca/img-libros/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
				tiposerv.setImagen(nombreImagen); 
			}

		}
		if(tiposerv.getId()==null)
			model.addFlashAttribute("msg","Servicio agregado");
		else model.addFlashAttribute("msg","Servicio modificado");
		serviceTiposServ.guardar(tiposerv);
		return "redirect:/tiposserv/indexPaginado";
		
		
	}
	
	@GetMapping("/nueva")
	public String nuevaTipos(TipoServ tiposerv) {
		return"tiposserv/formTipos";
	}
	
//	@GetMapping("/index")
//	public String mostrarIndex(Model model) {
//		List<TipoServ> tiposserv = serviceTiposServ.obtenerTiposServ();
//		model.addAttribute("tiposserv", tiposserv);
//		model.addAttribute("total", serviceTiposServ.totalTipoServ());
//		return"tiposserv/listaTipos";
//	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<TipoServ> lista = serviceTiposServ.buscarTodas(page); 
	model.addAttribute("tiposserv", lista);
	model.addAttribute("total", serviceTiposServ.totalTipoServ());
	return "tiposserv/listaTipos";
	}

}
