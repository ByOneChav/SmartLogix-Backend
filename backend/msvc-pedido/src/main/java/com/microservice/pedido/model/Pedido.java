package com.microservice.pedido.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Pedido que representa la tabla en la BD
 */
@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del pedido autogenerado", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Descripción del pedido", example = "Compra de PC")
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "Cantidad de productos", example = "2")
    private Integer cantidad;

    @Column(nullable = false)
    @Schema(description = "Precio total del pedido", example = "500000")
    private Integer precio;

    @Column(nullable = false)
    @Schema(description = "ID del inventario asociado", example = "10")
    private Long inventarioId;
}