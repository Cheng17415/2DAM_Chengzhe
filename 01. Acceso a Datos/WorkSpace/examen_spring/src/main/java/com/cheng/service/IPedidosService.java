package com.cheng.service;

import java.util.List;

import com.cheng.model.Pedido;

public interface IPedidosService {
	List<Pedido> buscarTodas();
	Pedido buscarPorId(Integer id);
	void guardar(Pedido pedido);
	void eliminar(Integer id);
}
