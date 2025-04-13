package io.paws.paws.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Генерация токена на основе email
    public String generateToken(String email) {
        long expiration = 1000 * 60 * 60 * 24; // 24 часа

        return Jwts.builder()
                .setSubject(email) // шифруем email
                .setIssuedAt(new Date()) // время создания токена
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // срок действия
                .signWith(secretKey)
                .compact(); // возвращает готовый токен
    }

    // Извлечение email из токена
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}