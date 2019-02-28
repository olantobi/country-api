/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferon.countryapi.security;

import com.africaprudential.backenddemo.model.AuthUser;
import com.africaprudential.backenddemo.model.User;
import com.africaprudential.backenddemo.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ebenezer
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    @Qualifier("pbkdf2PasswordEncoder")
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
        System.out.println("Authentication is about to happen");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Calling find by username");
        User user = accessControlService.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }

        if (!user.isEnabled())
            throw new MyAuthenticationException("User is not yet enabled on this platform");

        if (passwordEncoder.matches(password, user.getEncryptedPassword())) {
            AuthUser authUser = new AuthUser(username, password, getGrantedAuthorities(user));
            return authUser;
        } else {
            throw new MyAuthenticationException("Invalid username/password");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.clear();
        authorities.add(new SimpleGrantedAuthority(user.getRoleName()));

        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    class MyAuthenticationException extends AuthenticationException {

        public MyAuthenticationException(String msg) {
            super(msg);
        }

    }
}
