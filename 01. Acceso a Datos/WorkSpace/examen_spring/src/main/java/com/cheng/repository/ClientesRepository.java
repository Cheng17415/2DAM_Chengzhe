package com.cheng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer>{

}
