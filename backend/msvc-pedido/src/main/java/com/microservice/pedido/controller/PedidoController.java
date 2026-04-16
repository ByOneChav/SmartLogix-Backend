package com.microservice.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.pedido.model.Pedido;
import com.microservice.pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST de pedidos
 */
@RestController
@RequestMapping("/api/pedido")
@Tag(name = "Pedido", description = "Operaciones relacionadas a pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * LISTAR TODOS
     */
    @GetMapping("/all")
    @Operation(summary = "Listar todos los pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404", description = "No hay pedidos")
    })
    public ResponseEntity<?> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();

        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay pedidos");
        }
        return ResponseEntity.ok(pedidos);
    }

    /**
     * BUSCAR POR ID
     */
    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<?> findById(
            @Parameter(description = "ID del pedido", in = ParameterIn.PATH)
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }

    /**
     * CREAR PEDIDO
     */
    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado"),
            @ApiResponse(responseCode = "400", description = "Error en datos")
    })
    public ResponseEntity<?> savePedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevo = pedidoService.save(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear pedido");
        }
    }

    /**
     * ACTUALIZAR PEDIDO
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar pedido")
    public ResponseEntity<?> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido existente = pedidoService.findById(id);

            existente.setDescripcion(pedido.getDescripcion());
            existente.setCantidad(pedido.getCantidad());
            existente.setPrecio(pedido.getPrecio());
            existente.setInventarioId(pedido.getInventarioId());

            return ResponseEntity.ok(pedidoService.save(existente));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }

    /**
     * ELIMINAR PEDIDO
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar pedido")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        try {
            pedidoService.delete(id);
            return ResponseEntity.ok("Pedido eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar");
        }
    }

    /**
     * BUSCAR POR INVENTARIO
     */
    @GetMapping("/search-by-inventario/{inventarioId}")
    @Operation(summary = "Buscar pedidos por inventarioId")
    public ResponseEntity<?> findByInventario(@PathVariable Long inventarioId) {
        return ResponseEntity.ok(pedidoService.findByInventarioId(inventarioId));
    }
}