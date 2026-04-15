package com.microservice.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcPedidoApplication.class, args);
	}

}
