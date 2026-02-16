package com.cheng.controller;

import java.util.Date;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cheng.model.Perfil;
import com.cheng.model.Usuario;
import com.cheng.service.IClientesService;
import com.cheng.service.IPedidosService;
import com.cheng.service.IUsuariosService;

@Controller
public class HomeController {
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    
    @Autowired
   	private IUsuariosService serviceUsuarios;
    
    @Autowired
    private IClientesService serviceClientes;
    
    @Autowired
    private IPedidosService servicePedidos;
    @Autowired
    private PasswordEncoder passwordEncoder;
  
	@GetMapping("/")
	public String mostrarHome() {return "home";}
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {		
		
		if (auth == null) {
	        return "redirect:/login";
	    }
	    
	    String username = auth.getName();
	    // Verifica si el servicio no está devolviendo NULL
	    Usuario usuario = serviceUsuarios.buscarPorUsername(username);
	    
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
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		// Recuperamos el password en texto plano
		String pwdPlano = usuario.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		// Hacemos un set al atributo password (ya viene encriptado)
		usuario.setPassword(pwdEncriptado);	
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}
		
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
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
	
	@GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
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
