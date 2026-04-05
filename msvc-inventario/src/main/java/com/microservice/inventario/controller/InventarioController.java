package com.microservice.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.inventario.model.Inventario;
import com.microservice.inventario.service.InventarioService;

/**
 * Controlador REST que expone endpoints
 */
@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    /**
     * Crear curso
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveInventario(@RequestBody Inventario inventario) {
        inventarioService.save(inventario);
    }

    /**
     * Obtener todos los cursos
     */
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    /**
     * Buscar curso por ID
     */
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.findById(id));
    }

    /**
     * Obtener curso + estudiantes
     */
    @GetMapping("/search-pedido/{inventarioId}")
    public ResponseEntity<?> findPedidos(@PathVariable Long inventarioId) {
        return ResponseEntity.ok(inventarioService.findPedidosByInventarioId(inventarioId));
    }
}