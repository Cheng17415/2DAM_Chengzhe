package com.rayosoft.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rayosoft.model.Categoria;
import com.rayosoft.service.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	// Inyectamos una instancia desde nuestro ApplicationContext   
    @Autowired
   	private ICategoriasService serviceCategorias;
	  
    /**
	 * Metodo que muestra la lista de categorias sin paginacion
	 * @param model
	 * @param page
	 * @return
	 */
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Categoria> lista = serviceCategorias.buscarTodas();
    	model.addAttribute("categorias", lista);
		return "categorias/listCategorias";
	}
    
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Categoria> lista = serviceCategorias.buscarTodas(page);
		model.addAttribute("categorias", lista);
		return "categorias/listCategorias";
	}
    

	@GetMapping("/create")
	public String crear(Categoria categoria) {		
		return "categorias/formCategoria";
	}
	

	@PostMapping("/save")
	public String guardar(Categoria categoria, BindingResult result, Model model, RedirectAttributes attributes) {	
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "categorias/formCategoria";
		}	
				
		// Guadamos el objeto categoria en la bd
		serviceCategorias.guardar(categoria);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");
			
		//return "redirect:/categorias/index";
		return "redirect:/categorias/indexPaginate";		
	}
	
	/**
	 * Método para renderizar el formulario para editar una Categoría
	 * @param idCategoria
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCategoria, Model model) {		
		Categoria categoria = serviceCategorias.buscarPorId(idCategoria);			
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
	}
	
	/**
	 * Método para eliminar una Categoría de la base de datos
	 * @param idCategoria
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
		
		// Eliminamos la categoria.
		serviceCategorias.eliminar(idCategoria);
			
		attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
		//return "redirect:/categorias/index";
		return "redirect:/categorias/indexPaginate";
	}
		
}
