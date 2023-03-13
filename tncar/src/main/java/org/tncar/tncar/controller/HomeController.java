package org.tncar.tncar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String mostrarIndex() {
		return "perfilEmpleado";
		
		
	}

}
