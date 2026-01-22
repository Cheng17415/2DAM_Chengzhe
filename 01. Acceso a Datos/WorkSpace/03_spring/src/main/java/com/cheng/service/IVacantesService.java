package com.cheng.service;

import java.util.List;

import com.cheng.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
}