package com.cforcoins.security.springbootsecurityjwt.security;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cforcoins.security.springbootsecurityjwt.model.JwtAuthenticationToken;
import com.cforcoins.security.springbootsecurityjwt.model.JwtUser;
import com.cforcoins.security.springbootsecurityjwt.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();

         JwtUser jwtUser = validator.validate(token);

         //if (jwtUser == null || jwtUser.getRole() == "admin" )

        String role = jwtUser.getRole();

        System.out.println(jwtUser.toString() + "before if check");


        //if (jwtUser == null)
         if (!role.equals("admin"))
         {
             System.out.println(role);
             System.out.println(jwtUser.toString() + " Inside check");
             throw new RuntimeException("Token Invalid jaffa token");
         }


        // List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());

        // return new JwtUserDetails(jwtUser.getUserName(),jwtUser.getUserId(),token, grantedAuthorities);

        System.out.println(jwtUser.getRole() + "after if check");
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole());
        System.out.println(grantedAuthorities);
        return new JwtUserDetails(jwtUser.getUserName(), jwtUser.getId(),
                token,
                grantedAuthorities);

    }


    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
