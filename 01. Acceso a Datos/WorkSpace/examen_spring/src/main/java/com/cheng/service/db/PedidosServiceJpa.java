package com.cheng.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheng.model.Pedido;
import com.cheng.repository.PedidosRepository;
import com.cheng.service.IPedidosService;

@Service
public class PedidosServiceJpa implements IPedidosService{
	
	@Autowired
	private PedidosRepository pedidosRepo;
	
	@Override
	public List<Pedido> buscarTodas() {
		return pedidosRepo.findAll();
	}

	@Override
	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> optional = pedidosRepo.findById(id);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Pedido pedido) {
		pedidosRepo.save(pedido);
	}

	@Override
	public void eliminar(Integer id) {
		pedidosRepo.deleteById(id);
	}

}
