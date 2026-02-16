package com.cheng.service;

import java.util.List;

import com.cheng.model.Usuario;

public interface IUsuariosService {

	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorUsername(String username);
	Usuario buscarPorId(Integer idUsuario);
	int bloquear(int idUsuario);
	int activar(int idUsuario);
}
