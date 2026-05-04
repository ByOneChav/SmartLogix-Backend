package com.microservice.envio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidad Envio.
 * Representa el despacho físico creado a partir de un Pedido confirmado.
 * Estado inicial: PREPARANDO → EN_TRANSITO → ENTREGADO (o DEVUELTO)
 */
@Entity
@Table(name = "envio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "direccion_destino")
    private String direccionDestino;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estado;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
}
