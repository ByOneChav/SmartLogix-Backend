package com.microservice.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.pedido.model.Pedido;
import com.microservice.pedido.repository.PedidoRepository;

/**
 * Servicio principal que contiene toda la lógica de negocio
 * Ya NO usamos interfaz ni implementación separada
 */
@Service
public class PedidoService {

    // Inyección del repositorio para acceder a la BD
    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Obtener todos los pedidos
     */
    public List<Pedido> findAll() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    /**
     * Buscar pedido por ID
     */
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow();
    }

    /**
     * Guardar un pedido
     */
    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    /**
     * Buscar pedidos por inventarioId
     */
    public List<Pedido> findByInventarioId(Long inventarioId) {
        return pedidoRepository.findAllByInventarioId(inventarioId);
    }
}