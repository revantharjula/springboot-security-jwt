package com.cforcoins.security.springbootsecurityjwt.security;

import com.cforcoins.security.springbootsecurityjwt.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/hello/secured");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String header =  httpServletRequest.getHeader("Authorization");

       if (header==null || !header.startsWith("Token ")) {
       // if (header==null) {
           throw new RuntimeException("Invalid Header");
       }

        String authenticationToken = header.substring(6);

        System.out.println("Im here " + authenticationToken);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }


}
