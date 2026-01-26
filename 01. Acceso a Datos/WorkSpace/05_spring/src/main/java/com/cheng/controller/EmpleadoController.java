package com.cheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheng.service.IEmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	@Autowired
	IEmpleadoService serviciosEmpleados;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("empleados",serviciosEmpleados.buscarTodos());
		//Falta implementarlo en el HTML
		return "empleados/listEmpleados";
	}
	
}
