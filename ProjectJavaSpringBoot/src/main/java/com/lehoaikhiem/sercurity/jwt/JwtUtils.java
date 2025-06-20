package com.lehoaikhiem.sercurity.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User; // Đây là Spring Security User, không phải entity User của bạn
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Thời gian hết hạn của token tính bằng mili giây, lấy từ application.properties
    // jwt.expiration.ms=86400000 # 24 giờ
    @Value("${jwt.expiration.ms}")
    private int jwtExpirationMs;

    // Hàm này tạo ra khóa bí mật từ chuỗi jwtSecret để ký và giải mã JWT
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Tạo JWT từ thông tin xác thực (Authentication object)
    public String generateJwtToken(Authentication authentication) {
        // Lấy đối tượng User của Spring Security từ Authentication
        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())) // Đặt username làm subject của token
                .setIssuedAt(new Date()) // Thời gian tạo token
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Thời gian hết hạn
                .signWith(key(), SignatureAlgorithm.HS256) // Ký token với khóa và thuật toán HS256
                .compact(); // Xây dựng và nén token thành chuỗi
    }

    // Lấy username từ JWT
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Xác thực JWT
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}
