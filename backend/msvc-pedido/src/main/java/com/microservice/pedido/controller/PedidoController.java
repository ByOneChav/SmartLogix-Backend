package com.microservice.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.pedido.model.Pedido;
import com.microservice.pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que expone los endpoints
 */
@RestController
@RequestMapping("/api/pedido")
@Tag(name = "Pedido", description = "Operaciones relacionadas a pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Crear pedido
     */
    @Operation(summary = "Crear un nuevo pedido")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePedido(@RequestBody Pedido pedido) {
        pedidoService.save(pedido);
    }

    /**
     * Obtener todos los pedidos
     */
    @Operation(summary = "Listar todos los pedidos")
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    /**
     * Buscar por ID
     */
    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    /**
     * Buscar por inventarioId
     */
    @Operation(summary = "Buscar pedidos por inventarioId")
    @GetMapping("/search-by-inventario/{inventarioId}")
    public ResponseEntity<?> findByInventario(@PathVariable Long inventarioId) {
        return ResponseEntity.ok(pedidoService.findByInventarioId(inventarioId));
    }
}