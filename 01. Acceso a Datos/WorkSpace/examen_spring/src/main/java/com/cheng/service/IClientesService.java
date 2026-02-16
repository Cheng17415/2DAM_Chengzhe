package com.cheng.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cheng.model.Cliente;

public interface IClientesService {
	List<Cliente> buscarTodas();
	Cliente buscarPorId(Integer id);
	void guardar(Cliente cliente);
	void eliminar(Integer id);
}
