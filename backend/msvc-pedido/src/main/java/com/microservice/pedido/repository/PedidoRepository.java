package com.microservice.pedido.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.pedido.model.Pedido;

import java.util.List;

/**
 * Repositorio para acceso a datos
 */
@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    // Método personalizado (Spring lo genera automáticamente)
    List<Pedido> findAllByInventarioId(Long inventarioId);
}