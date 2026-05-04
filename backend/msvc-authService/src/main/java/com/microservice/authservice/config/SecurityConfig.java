package com.microservice.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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

    @Value("$app.cors.allowed-origins")
    private String allowedOrigins;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigins));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;

    }
}