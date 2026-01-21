package com.cheng.demo.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cheng.demo.model.Vacante;
import com.cheng.demo.service.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		String nombre = "Programador";
		Date fecha = new Date();
		double salario = 9000;
		boolean vigente = true;
		
		model.addAttribute("nombre",nombre);
		model.addAttribute("fecha", fecha);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);
		return "home";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos",lista);
		return "listado";
	}
	
	@GetMapping("/buscar/{id}")
	public String buscarPorId(
			@PathVariable("id") int n, Model model) {
		model.addAttribute("vacante",serviceVacantes.buscarPorId(n));
		return "buscar";
	}
	
	@GetMapping("/buscarYear/{year}")
	public String buscarPorYear(
			@PathVariable("year") int y, Model model) {
		model.addAttribute("year",y);
		model.addAttribute("vacantes", serviceVacantes.buscarPorYear(y));
		return "buscarYear";
		
	}
}
