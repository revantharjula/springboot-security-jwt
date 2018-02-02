package com.cforcoins.security.springbootsecurityjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/notsecured")
    public String hello(){
        return "hello";
    }


    @GetMapping("/secured")
    public String secured(){
        return "Secured hello";
    }


}
