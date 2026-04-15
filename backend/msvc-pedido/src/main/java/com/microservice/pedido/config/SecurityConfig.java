package com.microservice.pedido.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad básica
 * Permite acceso a Swagger y protege endpoints
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para APIs REST

            .authorizeHttpRequests(auth -> auth

                // Permitir Swagger sin autenticación
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // El resto requiere autenticación
                .anyRequest().authenticated()
            );

        return http.build();
    }
}