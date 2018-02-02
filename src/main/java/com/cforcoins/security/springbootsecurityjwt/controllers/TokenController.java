package com.cforcoins.security.springbootsecurityjwt.controllers;

import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;
import org.springframework.web.bind.annotation.*;
import com.cforcoins.security.springbootsecurityjwt.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

    @PostMapping
    public String generateToken(@RequestBody final JwtUser jwtUser)
    {
       JwtGenerator jwtGenerator = new JwtGenerator();
       jwtGenerator.generate(jwtUser);
       return null;
    }

}
