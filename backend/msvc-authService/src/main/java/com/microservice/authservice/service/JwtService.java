package com.microservice.authservice.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Manejo de JWT (versión moderna jjwt 0.11+)
 */
@Service
public class JwtService {

    // 🔐 Clave secreta (mínimo 256 bits)
    private final String SECRET = "my-super-secret-key-that-is-very-long-123456";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generar token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraer email
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Obtener claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}