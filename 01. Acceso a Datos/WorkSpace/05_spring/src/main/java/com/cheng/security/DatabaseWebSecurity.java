package com.cheng.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
	
	@Bean
	public UserDetailsManager usersCustom(DataSource dataSource) {
	    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
	    users.setUsersByUsernameQuery("select username, password, estatus from Usuarios where username=?");
	    users.setAuthoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " +
	                                        "inner join Usuarios u on u.id = up.idUsuario " +
	                                        "inner join Perfiles p on p.id = up.idPerfil " + 
	                                        "where u.username = ?");
	    return users;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authorize -> authorize

			// Los recursos estáticos no requieren autenticación
			.requestMatchers("/bootstrap/**", "/img/**", "/tinymce/**").permitAll()
	
			// Las vistas públicas no requieren autenticación
			.requestMatchers("/", "/login","/signup", "/search", "/bcrypt/**", "/about", "/index").permitAll()
	
			// Asignar permisos a URLs por ROLES
			.requestMatchers("/departamentos/index/**","/empleados/index/**", "/usuarios/index/**").hasAnyAuthority("USUARIO","SUPERVISOR","ADMINISTRADOR")
			.requestMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
			.requestMatchers("/empleados/**","/departamentos/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
	
			// Todas las demás URLs de la Aplicación requieren autenticación
			.anyRequest().authenticated());

		// El formulario de Login no requiere autenticacion
		http.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll());
		
		return http.build();
	}

	/**
	 * Implementación de Spring Security que encripta passwords con el algoritmo
	 * Bcrypt
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}