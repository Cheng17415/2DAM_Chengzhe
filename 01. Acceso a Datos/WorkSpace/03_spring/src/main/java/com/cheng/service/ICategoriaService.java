package com.cheng.service;

import java.util.List;

import com.cheng.model.Categoria;

public interface ICategoriaService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
}
