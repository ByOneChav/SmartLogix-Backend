package com.microservice.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.pedido.model.Pedido;
import com.microservice.pedido.service.PedidoService;

/**
 * Controlador REST que expone los endpoints
 */
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    // Inyección del servicio (YA NO ES INTERFAZ)
    @Autowired
    private PedidoService pedidoService;

    /**
     * Crear estudiante
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePedido(@RequestBody Pedido pedido) {
        pedidoService.save(pedido);
    }

    /**
     * Obtener todos los estudiantes
     */
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    /**
     * Buscar por ID
     */
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    /**
     * Buscar por inventarioId
     */
    @GetMapping("/search-by-inventario/{inventarioId}")
    public ResponseEntity<?> findByInventario(@PathVariable Long inventarioId) {
        return ResponseEntity.ok(pedidoService.findByInventarioId(inventarioId));
    }
}
