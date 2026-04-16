package com.microservice.pedido.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.pedido.model.Pedido;
import com.microservice.pedido.repository.PedidoRepository;

import jakarta.transaction.Transactional;

/**
 * Servicio que contiene la lógica de negocio
 */
@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    // Inyección por constructor (mejor práctica)
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Obtener todos los pedidos
     */
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    /**
     * Buscar pedido por ID
     */
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    /**
     * Guardar pedido
     */
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    /**
     * Eliminar pedido
     */
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    /**
     * Buscar por inventarioId
     */
    public List<Pedido> findByInventarioId(Long inventarioId) {
        return pedidoRepository.findAllByInventarioId(inventarioId);
    }
}