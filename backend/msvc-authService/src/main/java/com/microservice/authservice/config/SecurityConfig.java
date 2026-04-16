package com.microservice.authservice.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad del AuthService
 */
@Configuration
public class SecurityConfig {

    /**
     * 🔐 Bean para encriptar contraseñas con BCrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 🔒 Configuración de seguridad HTTP
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Desactiva CSRF (porque usamos API REST con JWT)
            .csrf(csrf -> csrf.disable())

            // 🔐 Configuración de autorizaciones
            .authorizeHttpRequests(auth -> auth

                // 🟢 ENDPOINTS PÚBLICOS (NO requieren token)
                .requestMatchers("/api/auth/**").permitAll()

                // 🟢 Swagger (para documentación)
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // 🔒 Cualquier otro endpoint requiere autenticación
                .anyRequest().authenticated()
            );

        return http.build();
    }
}