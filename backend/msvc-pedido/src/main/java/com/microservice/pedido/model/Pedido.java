package com.microservice.pedido.model;

import jakarta.persistence.*;
import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Entidad que representa la tabla pedido
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido")
@Schema(description = "Entidad Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del pedido", example = "1")
    private Long id;

    @Schema(description = "Descripción del pedido", example = "Compra de laptop")
    private String descripcion;

    @Column(name = "cantidad")
    @Schema(description = "Cantidad de productos", example = "2")
    private Integer cantidad;

    @Schema(description = "Precio del pedido", example = "500000")
    private Integer precio;

    @Column(name = "inventario_id")
    @Schema(description = "ID del inventario relacionado", example = "10")
    private Long inventarioId;
}