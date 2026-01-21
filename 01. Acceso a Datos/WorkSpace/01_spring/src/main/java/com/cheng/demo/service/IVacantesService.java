package com.cheng.demo.service;

import java.util.List;

import com.cheng.demo.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	List<Vacante> buscarPorYear(Integer year);
	Vacante eliminarVacante(Integer idVacante);
}
