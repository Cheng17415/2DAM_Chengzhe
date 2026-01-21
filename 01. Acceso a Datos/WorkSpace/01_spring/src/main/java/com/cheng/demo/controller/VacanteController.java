package com.cheng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheng.demo.service.IVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacanteController {
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, Model model) {
		String mensaje ="";
		model.addAttribute("id",idVacante);
		
		if(serviceVacantes.eliminarVacante(idVacante) != null) {
			mensaje="Borrando vacante con id: " + idVacante;
		}else {
			mensaje ="No se ha podido borrar la vacante con id: " + idVacante;
		}
		model.addAttribute("mensaje",mensaje);
		return "vacantes/mensaje";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		model.addAttribute("vacantes", serviceVacantes.buscarTodas());
		return "vacantes/tabla";
	}
}
