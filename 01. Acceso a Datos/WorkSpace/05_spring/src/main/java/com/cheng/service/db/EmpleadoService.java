package com.cheng.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.model.Empleado;
import com.cheng.repository.EmpleadoRepository;
import com.cheng.service.IEmpleadoService;

@Service
public class EmpleadoService implements IEmpleadoService{
	
	@Autowired
	EmpleadoRepository empleadoRepo;

	@Override
	public void guardar(Empleado emp) {
		empleadoRepo.save(emp);
	}

	@Override
	public List<Empleado> buscarTodos() {
		return empleadoRepo.findAll();
	}

	@Override
	public Empleado buscarPorId(Integer idEmpleado) {
		Optional<Empleado> opcional = empleadoRepo.findById(idEmpleado);
		if(opcional.isPresent()) {
			return opcional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idEmp) {
		empleadoRepo.deleteById(idEmp);
	}

}
