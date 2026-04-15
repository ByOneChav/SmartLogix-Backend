package com.microservice.inventario.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.microservice.inventario.dto.PedidoDTO;

/**
 * Cliente Feign para comunicarse con microservicio student
 */
@FeignClient(name = "msvc-pedido", url = "http://localhost:8082")
public interface PedidoClient {

    /**
     * Obtiene estudiantes por courseId desde otro microservicio
     */
    @GetMapping("/api/pedido/search-by-inventario/{inventarioId}")
    List<PedidoDTO> findAllProductoByInventario(@PathVariable Long inventarioId);
}