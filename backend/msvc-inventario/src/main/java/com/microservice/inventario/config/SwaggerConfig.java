package com.microservice.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuración de Swagger/OpenAPI para el microservicio de Inventario
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()

                // 🌐 API Gateway como punto de entrada (igual que Pedido)
                .addServersItem(new Server().url("http://localhost:8080"))

                // 📌 Información del microservicio
                .info(
                    new Info()
                        .title("Microservicio de Gestión de Inventario - SMARTLOGIX")
                        .version("v1.0")
                        .description(
                            "Este microservicio forma parte de la arquitectura de SMARTLOGIX y se encarga de la gestión del inventario.\n\n" +

                            "📦 Funcionalidades principales:\n" +
                            "- Registrar productos en inventario\n" +
                            "- Consultar productos disponibles\n" +
                            "- Actualizar stock y ubicación\n" +
                            "- Eliminar productos\n" +
                            "- Integración con microservicio de pedidos\n\n" +

                            "🔗 Este servicio se comunica con el microservicio de PEDIDOS mediante FeignClient para obtener los pedidos asociados a un inventario."
                        )
                );
    }
}