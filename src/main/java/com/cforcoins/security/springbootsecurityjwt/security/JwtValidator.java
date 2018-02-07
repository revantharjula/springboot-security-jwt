package com.cforcoins.security.springbootsecurityjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private String secret = "revanth";

    public JwtUser validate(String token){

        JwtUser jwtUser = null;

        try {

            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUserName(body.getSubject());
            System.out.println(jwtUser.getUserName());
            jwtUser.setId(Long.parseLong((String) body.get("id")));
            jwtUser.setRole((String) body.get("role"));

        }
        catch (Exception e){
          System.out.println(e);
        }
        return jwtUser;
    }
}
