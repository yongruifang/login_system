package org.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate jwt token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        System.out.println("!!!!!!!!!!!!!!!!!");
        System.out.println("getName : " + username);
        System.out.println("!!!!!!!!!!!!!!!!!");
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token  = Jwts.builder()
                .subject((username))
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }
    private Key key (){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }
    // 从 Jwt Token中获取用户名
    public String getUsername(String token) {
       Claims claims =  Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
       String username  = claims.getSubject();
        return username;
    }
    // 验证 JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException e) {
            System.out.println("Invalid JWT Token : " + e.getMessage());
        }catch (ExpiredJwtException e) {
            System.out.println("JWT Token is expired: " + e.getMessage());
        }catch (UnsupportedJwtException e) {
            System.out.println("JWT Token is unsupported: " + e.getMessage());
        }catch (IllegalArgumentException e) {
            System.out.println("JWT Claims string is empty: " + e.getMessage());
        }
        return false;
    }
}
