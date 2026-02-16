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

import com.cheng.model.Pedido;
import com.cheng.service.IPedidosService;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {
	@Autowired
    private IPedidosService servicePedidos;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("pedidos",servicePedidos.buscarTodas());
		//Falta implementarlo en el HTML
		return "pedidos/listPedidos";
	}
	
	@GetMapping("/create")
	public String crear(Pedido pedido, Model model) {
		return "pedidos/formPedido";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("pedido", servicePedidos.buscarPorId(id));
		return "pedidos/formPedido";
	}
	@PostMapping("/save")
	public String guardar(Pedido pedido, BindingResult result, RedirectAttributes atr) {
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "pedidos/formPedido";
		}
		servicePedidos.guardar(pedido);
		System.out.println("Pedido " + pedido);
		atr.addFlashAttribute("msg", "Pedido guardado");
		return "redirect:/pedidos/index";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id, RedirectAttributes atr) {
		System.out.println("Eliminando pedido con id " + id);
		servicePedidos.eliminar(id);
		atr.addFlashAttribute("msg", "Pedido con id " + id + " fue eliminado");
		return "redirect:/pedidos/index";
	}
}
