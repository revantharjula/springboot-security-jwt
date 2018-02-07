package com.cforcoins.security.springbootsecurityjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(JwtUser jwtuser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtuser.getUserName());
        claims.put("id", String.valueOf(jwtuser.getId()));
        claims.put("role", jwtuser.getRole());

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "revanth")
                .compact();

        return token;
    }
}
