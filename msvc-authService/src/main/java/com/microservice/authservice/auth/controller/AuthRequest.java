package com.microservice.authservice.auth.controller;

public record AuthRequest(
    String email,
    String password
) {
} 
