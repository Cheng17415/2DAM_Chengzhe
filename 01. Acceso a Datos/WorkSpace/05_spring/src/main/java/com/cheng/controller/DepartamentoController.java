package com.cheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheng.service.IDepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	@Autowired
	public IDepartamentoService serviciosDept;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("departamentos",serviciosDept.buscarTodos());
		return "departamentos/listDepartamentos";
	}
}
