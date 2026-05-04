package com.microservice.envio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cliente Feign para notificar cambios de estado al msvc-pedido.
 * Se llama al crear un envío (pedido → ENVIADO) y al entregar (pedido → ENTREGADO).
 */
@FeignClient(name = "msvc-pedido", url = "${msvc-pedido.url:}")
public interface PedidoClient {

    @PutMapping("/api/pedido/update/{id}/estado")
    void cambiarEstado(@PathVariable("id") Long id, @RequestParam("estado") String estado);
}
