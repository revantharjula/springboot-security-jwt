package com.cforcoins.security.springbootsecurityjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;

public class JwtGenerator {
    public String generate(JwtUser jwtuser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtuser.getUserName());
        claims.put("userId", String.valueOf(jwtuser.getUserId()));
        claims.put("role", jwtuser.getRole());

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "revanth")
                .compact();

        return token;
    }
}
