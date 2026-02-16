package com.cheng.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheng.model.Cliente;
import com.cheng.repository.ClientesRepository;
import com.cheng.service.IClientesService;

@Service
public class ClientesServiceJpa implements IClientesService{
	@Autowired
	private ClientesRepository clientesRepo;
	public void guardar(Cliente cliente) {	clientesRepo.save(cliente);	}

	public List<Cliente> buscarTodas() {return clientesRepo.findAll();	}

	public Cliente buscarPorId(Integer idCliente) {
		Optional<Cliente> optional = clientesRepo.findById(idCliente);
		if (optional.isPresent()) return optional.get();
		return null;  
	}
	public void eliminar(Integer idCliente) {clientesRepo.deleteById(idCliente);}
}
