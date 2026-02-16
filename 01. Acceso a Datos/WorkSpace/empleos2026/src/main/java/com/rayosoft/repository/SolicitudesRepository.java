package com.rayosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayosoft.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
