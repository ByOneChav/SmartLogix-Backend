package com.microservice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.inventario.model.Inventario;

/**
 * Repositorio JPA para Inventario
 * Permite acceso a la base de datos
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}