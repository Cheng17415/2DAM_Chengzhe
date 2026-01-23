package com.rayosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayosoft.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

}
