package com.technical_proof.loginbackend.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.technical_proof.loginbackend.dto.ErrorResponse;
import com.technical_proof.loginbackend.dto.LoginRequest;
import com.technical_proof.loginbackend.dto.LoginResponse;
import com.technical_proof.loginbackend.dto.TokenRefreshRequest;
import com.technical_proof.loginbackend.security.JwtUtil;
import com.technical_proof.loginbackend.service.AuthService;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        try {
            String newToken = jwtUtil.refreshToken(request.getToken());
            return ResponseEntity.ok(new LoginResponse(newToken));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("invalid_token", "No se pudo refrescar el token: " + e.getMessage()));
        }
    }

    @GetMapping("/sso")
    public ResponseEntity<Void> startSSOLogin() {
        String simulatedAuthCode = "simulated-code-123";
        
        // Redirect directly to the frontend simulating the provider
        URI redirectUri = URI.create("http://localhost:4200/sso/callback?code=" + simulatedAuthCode);
        
        return ResponseEntity.status(HttpStatus.FOUND)
                            .location(redirectUri)
                            .build();
    }

    @GetMapping(value = "/sso/callback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleSsoCallback(@RequestParam(value = "code", required = false) String code) {
        if (code == null || !code.equals("simulated-code-123")) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("invalid_code", "El código de autorización es inválido o no fue proporcionado."));
        }
        String simulatedEmail = "usuario@sso.com";
        String token = jwtUtil.generateToken(simulatedEmail);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
