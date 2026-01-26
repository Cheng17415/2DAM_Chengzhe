package com.cheng.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.model.Departamento;
import com.cheng.repository.DepartamentoRepository;
import com.cheng.service.IDepartamentoService;

@Service
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepo;
	
	@Override
	public void guardar(Departamento dept) {
		departamentoRepo.save(dept);
	}

	@Override
	public List<Departamento> buscarTodos() {
		return departamentoRepo.findAll();
	}

	@Override
	public Departamento buscarPorId(Integer idDept) {
		Optional<Departamento> optional = departamentoRepo.findById(idDept);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void eliminar(Integer idDept) {
		departamentoRepo.deleteById(idDept);
	}

}
