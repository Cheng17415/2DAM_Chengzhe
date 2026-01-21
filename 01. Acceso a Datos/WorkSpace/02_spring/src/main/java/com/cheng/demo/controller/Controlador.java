package com.cheng.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cheng.demo.model.Biblioteca;
import com.cheng.demo.model.Departamento;

@Controller
public class Controlador {
	@GetMapping("/")
	private String home(Model model) {
		List <Departamento> departamentos = Biblioteca.getDeps();
		model.addAttribute("departamentos",departamentos);
		return "home";
	}
	
	@GetMapping("/detalle")
	private String detalle(Model model) {
		//Crear un modelo de un numero entero
		//Pasarlo al template y que muestre la tabla de multiplicar de ese número
		model.addAttribute("num", Biblioteca.alea(1, 10));
		return "detalle";
	}
	
	@GetMapping("/detalleNew/{id}")
	public String mostrarDetalleNew(
			@PathVariable("id") int numero, Model model) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(int i= 1; i <=numero; i++) {
			lista.add(i);
		}
		model.addAttribute("numero",numero);
		model.addAttribute("lista",lista);
		return "detalleNew";
	}
}
