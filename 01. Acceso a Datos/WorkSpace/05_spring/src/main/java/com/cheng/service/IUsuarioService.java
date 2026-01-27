package com.cheng.service;

import java.util.List;

import com.cheng.model.Usuario;


public interface IUsuarioService {

	void guardar(Usuario usuario);
	
	// Ejercicio: Método que elimina un usuario de la base de datos.
	void eliminar(Integer idUsuario);
	
	// Ejercicio: Implementar método que recupera todos los usuarios. Usar vista de listUsuarios.html
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorUsername(String username);
	Usuario buscarPorId(Integer idUsuario);
	int bloquear(int idUsuario);
	int activar(int idUsuario);
}

// Agregar al archivo menu.html el link para acceder al listado de Usuarios y configurar el link del botón Registrarse

