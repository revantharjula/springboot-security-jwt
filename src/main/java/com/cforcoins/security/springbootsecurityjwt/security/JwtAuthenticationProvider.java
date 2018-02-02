package com.cforcoins.security.springbootsecurityjwt.security;

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
    private JwtValidator jwtValidator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();

         JwtUser jwtUser = jwtValidator.validate(token);

         if (jwtUser == null)
         {
             throw new RuntimeException("Token Invalid");
         }

         List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());

         return new JwtUserDetails(jwtUser.getUserName(),jwtUser.getUserId(),token, grantedAuthorityList);

    }


    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
