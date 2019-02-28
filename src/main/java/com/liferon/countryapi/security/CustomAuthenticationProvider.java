/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferon.countryapi.security;

import com.liferon.countryapi.domain.User;
import com.liferon.countryapi.model.AuthUser;
import com.liferon.countryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
import java.util.Optional;

/**
 *
 * @author Ebenezer
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userService;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();        
        
        Optional<User> userOptional = userService.findByUsername(username);
        
        if(!userOptional.isPresent()) {            
            throw new UsernameNotFoundException("Invalid username/password");
        }
        
        User user = userOptional.get();
        
        if (passwordEncoder.matches(password, user.getPassword())) {                                                    
            AuthUser authUser = new AuthUser(username, password, getGrantedAuthorities(user));
            return authUser;
        } else { 
            throw new MyAuthenticationException("Invalid username/password");
        }
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.clear();        
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
       
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
