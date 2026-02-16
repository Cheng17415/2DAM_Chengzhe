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

import com.cheng.model.Cliente;
import com.cheng.service.IClientesService;

@Controller
@RequestMapping("clientes")
public class ClientesController {
	@Autowired
    private IClientesService serviceClientes;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("clientes",serviceClientes.buscarTodas());
		return "clientes/listClientes";
	}
	
	@GetMapping("/create")
	public String crear(Cliente cliente, Model model) {
		return "clientes/formCliente";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("cliente", serviceClientes.buscarPorId(id));
		return "clientes/formCliente";
	}
	@PostMapping("/save")
	public String guardar(Cliente cliente, BindingResult result, RedirectAttributes atr) {
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "clientes/formCliente";
		}
		serviceClientes.guardar(cliente);
		System.out.println("Cliente " + cliente);
		atr.addFlashAttribute("msg", "Cliente guardado");
		return "redirect:/clientes/index";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id, RedirectAttributes atr) {
		System.out.println("Eliminando cliente con id " + id);
		serviceClientes.eliminar(id);
		atr.addFlashAttribute("msg", "Cliente con id " + id + " fue eliminado");
		return "redirect:/clientes/index";
	}
}
