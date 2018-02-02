package com.cforcoins.security.springbootsecurityjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cforcoins.security.springbootsecurityjwt.security.JwtAuthenticationEntryPoint;
import com.cforcoins.security.springbootsecurityjwt.security.JwtAuthenticationFilter;
import com.cforcoins.security.springbootsecurityjwt.security.JwtAuthenticationProvider;
import com.cforcoins.security.springbootsecurityjwt.security.JwtSuccessHandler;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {


    private JwtAuthenticationProvider authenticationProvider;
    private JwtAuthenticationEntryPoint entrypoint;

    @Bean
    public AuthenticationManager authenticationManager()
    {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter()
    {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("**/rest/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entrypoint);

        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
