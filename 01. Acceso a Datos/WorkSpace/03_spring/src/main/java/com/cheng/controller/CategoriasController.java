package com.cheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.cheng.service.ICategoriaService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	private ICategoriaService serviceCategorias;
	
	// @GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "categorias/listCategorias";
	}
	// @GetMapping("/create")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear() {
		return "categorias/formCategoria";
	}
	// @PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
		System.out.println("Categoria:" + nombre);
		System.out.println("Descripcion: " + descripcion);
		
		return "categorias/listCategorias";
	}
	
}
