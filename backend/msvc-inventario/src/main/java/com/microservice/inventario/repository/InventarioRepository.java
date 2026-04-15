package com.microservice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.inventario.model.Inventario;

/**
 * Repositorio para acceso a base de datos
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
