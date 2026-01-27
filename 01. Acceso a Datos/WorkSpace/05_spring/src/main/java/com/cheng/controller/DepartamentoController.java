package com.cheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cheng.model.Departamento;
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
	
	@GetMapping("/create")
	public String crear(Departamento dept,Model model) {
		return "departamentos/formDepartamento";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable int id,Model model) {
		model.addAttribute("departamento", serviciosDept.buscarPorId(id));
		return "departamentos/formDepartamento";
	}
	
	@PostMapping("/save")
	public String guardar(Departamento dept, BindingResult result,RedirectAttributes atr) {
		if(result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "departamentos/formDepartamento";
		}
		serviciosDept.guardar(dept);
		System.out.println("Departamento " + dept);
		atr.addFlashAttribute("msg", "Departamento guardado");
		return "redirect:/departamentos/index";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id, RedirectAttributes atr) {
		System.out.println("Eliminando departamento con id " + id);
		serviciosDept.eliminar(id);
		atr.addFlashAttribute("msg", "Departamento con id " + id + " fue eliminado");
		return "redirect:/departamentos/index";
	}
}
