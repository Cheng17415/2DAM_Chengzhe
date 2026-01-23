package com.cheng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

}
