package com.rayosoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayosoft.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer>{
	//Show AOT-generated Implementation, Query, etc..
	List<Vacante> findByEstatus(String estatus);
	//Show AOT-generated Implementation, Query, etc..
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	//Show AOT-generated Implementation, Query, etc..
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	//Show AOT-generated Implementation, Query, etc..
	List<Vacante> findByEstatusIn(String[] estatus);
}
