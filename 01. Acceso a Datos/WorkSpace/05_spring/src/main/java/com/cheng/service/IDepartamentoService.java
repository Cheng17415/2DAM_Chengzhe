package com.cheng.service;

import java.util.List;

import com.cheng.model.Departamento;

public interface IDepartamentoService {
	void guardar(Departamento dept);
	void eliminar(Integer idDept);
	List<Departamento> buscarTodos();
	Departamento buscarPorId(Integer idDept);
	
}
