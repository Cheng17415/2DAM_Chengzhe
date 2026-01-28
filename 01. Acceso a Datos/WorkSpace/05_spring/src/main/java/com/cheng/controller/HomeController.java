package com.cheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheng.service.IDepartamentoService;
import com.cheng.service.IEmpleadoService;
import com.cheng.service.IUsuarioService;
import com.cheng.model.Usuario;

import ch.qos.logback.core.model.Model;
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
		String username = auth.getName();
		
		for(GrantedAuthority rol :auth.getAuthorities()) {
			System.out.println("ROL" + rol.getAuthority());
		}
		
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = serviceUsu.buscarPorUsername(username);
			usuario.setPassword(null);
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
}
