package com.cheng.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheng.demo.model.Vacante;

@Controller
public class HomeController {
	
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
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes", lista);
		return "tabla";
	}
	
	private List<Vacante> getVacantes(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		try {
			//Creamos la oferta de Trabajo 1
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos Ing. Civil para");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa importante solicita");
			vacante2.setFecha(sdf.parse("11-06-2012"));
			vacante2.setSalario(7000.0);
			vacante2.setDestacado(1);
			vacante2.setImagen("empresa2.png");
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Guardia Civil");
			vacante3.setDescripcion("Solicitamos Guardia Civil para");
			vacante3.setFecha(sdf.parse("08-02-1999"));
			vacante3.setSalario(8500.0);
			vacante3.setDestacado(0);
			vacante3.setImagen("empresa3.png");
			
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Grafico");
			vacante4.setDescripcion("Solicitamos Guardia Civil para");
			vacante4.setFecha(sdf.parse("08-02-2019"));
			vacante4.setSalario(8500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("logo.png");
			
			Vacante vacante5 = new Vacante();
			vacante5.setId(5);
			vacante5.setNombre("Guardia Civil");
			vacante5.setDescripcion("Solicitamos Guardia Civil para");
			vacante5.setFecha(sdf.parse("08-02-1999"));
			vacante5.setSalario(8500.0);
			vacante5.setDestacado(1);
			vacante5.setImagen("no-image.png");
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			lista.add(vacante5);
		}catch(ParseException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return lista;
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
}
