package com.cheng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.model.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer>{

}
