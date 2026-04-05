package com.microservice.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.inventario.client.PedidoClient;
import com.microservice.inventario.dto.PedidoDTO;
import com.microservice.inventario.http.PedidoByInventarioResponse;
import com.microservice.inventario.model.Inventario;
import com.microservice.inventario.repository.InventarioRepository;

import java.util.List;

/**
 * Servicio principal con toda la lógica de negocio
 */
@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private PedidoClient pedidoClient;

    /**
     * Obtener todos los cursos
     */
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    /**
     * Buscar curso por ID
     */
    public Inventario findById(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    /**
     * Guardar curso
     */
    public void save(Inventario inventario) {
        inventarioRepository.save(inventario);
    }

    /**
     * Obtener curso + estudiantes asociados (microservicio)
     */
    public PedidoByInventarioResponse findPedidosByInventarioId(Long inventarioId) {

        // 1. Buscar curso en BD
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        // 2. Consumir microservicio student
        List<PedidoDTO> pedidos = pedidoClient.findAllProductoByInventario(inventarioId);

        // 3. Armar respuesta
        return PedidoByInventarioResponse.builder()
                .nombreProducto(inventario.getNombreProducto())
                .ubicacion(inventario.getUbicacion())
                .pedidoList(pedidos)
                .build();
    }
}