package com.technical_proof.loginbackend.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "claveSecreta123";
    private static final long EXPIRATION_TIME = 1000 * 60 * 30; // 30 minutos

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            return !getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String refreshToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            Date now = new Date();

            if (expiration.before(now)) {
                throw new RuntimeException("Token expirado, no puede ser refrescado");
            }

            String email = claims.getSubject();
            return generateToken(email);

        } catch (Exception e) {
            throw new RuntimeException("Token inválido");
        }
    }


    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
