package com.cheng.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cheng.service.IDepartamentoService;
import com.cheng.service.IEmpleadoService;
import com.cheng.service.IUsuarioService;
import com.cheng.model.Perfil;
import com.cheng.model.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	IDepartamentoService serviceDept;

	@Autowired
	IEmpleadoService serviceEmp;

	@Autowired
	IUsuarioService serviceUsu;

	@Autowired
	PasswordEncoder codificador;

	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}

	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
	    if (auth == null) {
	        return "redirect:/login";
	    }
	    
	    String username = auth.getName();
	    // Verifica si el servicio no está devolviendo NULL
	    Usuario usuario = serviceUsu.buscarPorUsername(username);
	    
	    if (usuario != null) {
	        usuario.setPassword(null);
	        session.setAttribute("usuario", usuario);
	    }
	    
	    return "redirect:/";
	}

	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}

	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes atr) {
		usuario.setPassword(codificador.encode(usuario.getPassword()));
		usuario.setEstatus(1);
		usuario.setFechaRegistro(LocalDate.now());

		Perfil perfil = new Perfil();
		perfil.setId(3);
		usuario.agregar(perfil);
		serviceUsu.guardar(usuario);
		atr.addFlashAttribute("msg","Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	@GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + codificador.encode(texto);
   	}
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
