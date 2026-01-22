package com.cheng.service;

import java.util.List;

import com.cheng.model.Empleado;

public interface IEmpleadoService {
	void guardar(Empleado emp);
	List<Empleado> buscarTodos();
	Empleado buscarPorId(Integer idEmpleado);
}
