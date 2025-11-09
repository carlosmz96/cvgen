package dev.carlosmz96.cvgen.cvgen_api.security.services;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final Key key;
    private final long expirationMs; // Access token
    private final long refreshExpirationMs; // Refresh token

    public JwtService(
            @Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.expiration-ms}") long expirationMs,
            @Value("${app.security.jwt.refresh-expiration-ms}") long refreshExpirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
    }

    /** 🔹 Access token normal */
    public String generateToken(String subject, Map<String, Object> claims) {
        return buildToken(subject, claims, expirationMs, "access");
    }

    /** 🔹 Refresh token más largo */
    public String generateRefreshToken(String subject) {
        return buildToken(subject, Map.of("typ", "refresh"), refreshExpirationMs, "refresh");
    }

    private String buildToken(String subject, Map<String, Object> claims, long expMs, String type) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expMs);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .claim("token_type", type)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractSubject(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean isValid(String token, String expectedSubject) {
        try {
            var body = parse(token).getBody();
            return body.getSubject().equals(expectedSubject)
                    && body.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isRefreshToken(String token) {
        try {
            var body = parse(token).getBody();
            Object type = body.get("token_type");
            return "refresh".equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

}
