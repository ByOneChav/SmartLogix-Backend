package com.microservice.pedido.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa la tabla students
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Column(name = "cantidad")
    private Integer cantidad;

    private Integer precio;

    @Column(name = "inventario_id")
    private Long inventarioId;
}