package me.anjwoc.sns.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtils {
    public static Boolean validate(String token, String email, String key) {
        String emailedByToken = getEmail(token, key);
        return emailedByToken.equals(email) && !isTokenExpired(token, key);
    }

    public static Claims extractAllClaims(String token, String key) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey(key))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public static String getEmail(String token, String key) {
        return extractAllClaims(token, key).get("email", String.class);
    }

    public static Boolean isTokenExpired(String token, String key) {
        Date expiration = extractAllClaims(token, key).getExpiration();
        return expiration.before(new Date());
    }

    public static Key getSigningKey(String secretKey) {
        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public static String generateAccessToken(String email, String key, long expiredAt) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiredAt))
            .signWith(getSigningKey(key), SignatureAlgorithm.HS256)
            .compact();
    }
}
























