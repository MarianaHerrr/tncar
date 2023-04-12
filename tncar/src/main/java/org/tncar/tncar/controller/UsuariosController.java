package org.tncar.tncar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tncar.tncar.entity.Usuario;
import org.tncar.tncar.service.IntServiceUsuarios;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	
//	@GetMapping("/index")
//	public String mostrarIndex(Model model) {
//		model.addAttribute("usuarios", serviceUsuarios.obtnerUsuarios());
//		System.out.println(serviceUsuarios.obtnerUsuarios());
//		return "usuarios/listaUsuarios";
//	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Usuario> lista = serviceUsuarios.buscarTodas(page); 
	model.addAttribute("usuarios", lista);
	model.addAttribute("total", serviceUsuarios.totalUsuarios());
	return "usuarios/listaUsuarios";
	}

}
