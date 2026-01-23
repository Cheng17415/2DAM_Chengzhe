package com.cheng.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheng.model.Vacante;
import com.cheng.repository.VacantesRepository;
import com.cheng.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {
	
	@Autowired
	private VacantesRepository vacantesRepo;

	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional = vacantesRepo.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepo.save(vacante);
	}
	
	@Override
	public List<Vacante> buscarDestacados() {
		return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}
	@Override
	public void eliminar(Integer idVacante) {
		vacantesRepo.deleteById(idVacante);
	}
	
	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example){
		return vacantesRepo.findAll(example);
	}
	
	@Override
	public Page<Vacante> buscarTodas(Pageable page){
		return vacantesRepo.findAll(page);
	}

	
	
}
