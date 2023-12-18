package com.snowball.backend.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    @Value("${secret.key}")
    private String secretKey;

    // Jwt 토큰을 발급하는 메소드
    public String generateToken(Long userId, long expirationMillis) {
        log.info("Jwt 토큰이 발행되었습니다.");
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 응답 헤더에서 Jwt 토큰을 반환하는 메소드
    public String getTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    // Jwt 토큰을 검증하고, 유저 id를 반환하는 메소드
    public Long getUserIdFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            log.info("유저 id를 반환합니다.");
            return Long.parseLong(body.getSubject());
        } catch (JwtException | IllegalArgumentException e) {
            // 토큰이 유효하지 않은 경우
            log.warn("유효하지 않은 토큰입니다.");
            return null;
        }
    }

}
