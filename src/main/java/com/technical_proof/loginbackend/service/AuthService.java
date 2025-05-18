package com.technical_proof.loginbackend.service;

import org.springframework.stereotype.Service;
import com.technical_proof.loginbackend.model.User;
import com.technical_proof.loginbackend.repository.UserRepository;
import com.technical_proof.loginbackend.security.JwtUtil;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtUtil.generateToken(email);
    }
}
