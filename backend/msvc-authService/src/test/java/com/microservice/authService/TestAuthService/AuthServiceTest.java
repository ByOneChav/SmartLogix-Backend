package com.microservice.authService.TestAuthService;


import com.microservice.authservice.dto.*;
import com.microservice.authservice.model.User;
import com.microservice.authservice.repository.UserRepository;
import com.microservice.authservice.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario de AuthService
 */
public class AuthServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final JwtService jwtService = new JwtService();
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    private final AuthService authService =
            new AuthService(userRepository, jwtService, passwordEncoder);

    @Test
    void testLoginSuccess() {

        AuthRequest request = new AuthRequest();
        request.setEmail("test@test.com");
        request.setPassword("1234");

        User user = User.builder()
                .email("test@test.com")
                .password("encoded")
                .build();

        Mockito.when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        Mockito.when(passwordEncoder.matches("1234", "encoded"))
                .thenReturn(true);

        AuthResponse response = authService.login(request);

        assertNotNull(response.getToken());
    }
}