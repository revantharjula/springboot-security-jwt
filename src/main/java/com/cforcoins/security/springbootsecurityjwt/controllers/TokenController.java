package com.cforcoins.security.springbootsecurityjwt.controllers;

import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cforcoins.security.springbootsecurityjwt.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping
    public String generateToken(@RequestBody final JwtUser jwtUser)
    {
       return jwtGenerator.generate(jwtUser);

    }

}
