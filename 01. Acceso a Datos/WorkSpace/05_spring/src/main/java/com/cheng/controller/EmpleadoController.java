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

import com.cheng.model.Empleado;
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
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("empleado", serviciosEmpleados.buscarPorId(id));
		return "empleados/formEmpleado";
	}
	@PostMapping("/save")
	public String guardar(Empleado empleado, BindingResult result, RedirectAttributes atr) {
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "empleados/formEmpleado";
		}
		serviciosEmpleados.guardar(empleado);
		System.out.println("Empleado " + empleado);
		atr.addFlashAttribute("msg", "Empleado guardado");
		return "redirect:/empleados/index";
	}
	
	@GetMapping("/create")
	public String crear(Empleado empleado, Model model) {
		return "empleados/formEmpleado";
	}
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id, RedirectAttributes atr) {
		System.out.println("Eliminando Empleado con id " + id);
		serviciosEmpleados.eliminar(id);
		atr.addFlashAttribute("msg", "Empleado con id " + id + " fue eliminado");
		return "redirect:/empleados/index";
	}
	
}
