package com.cforcoins.security.springbootsecurityjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private String secret = "revanth";

    JwtUser jwtUser = null;


    public JwtUser validate(String token){
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setUserId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("Role"));

        }
        catch (Exception e){
          System.out.println(e);
        }
        return jwtUser;
    }
}
