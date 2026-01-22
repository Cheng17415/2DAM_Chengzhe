package com.cheng.service;

import java.util.List;

import com.cheng.model.Departamento;

public interface IDepartamentoService {
	void guardar(Departamento dept);
	List<Departamento> buscarTodos();
	Departamento buscarPorId(Integer idDept);
}
