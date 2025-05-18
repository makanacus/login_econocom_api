package com.technical_proof.loginbackend.dto;

public class LoginResponse {
    private String token;

    // Constructor
    public LoginResponse(String token) {
        this.token = token;
    }

    // Getters
    public String getToken() {
        return token;
    }
}
