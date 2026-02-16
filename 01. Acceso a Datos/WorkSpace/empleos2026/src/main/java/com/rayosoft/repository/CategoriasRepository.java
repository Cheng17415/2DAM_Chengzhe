package com.rayosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayosoft.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}
