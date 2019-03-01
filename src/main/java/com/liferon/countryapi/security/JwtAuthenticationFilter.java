package com.liferon.countryapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferon.countryapi.model.LoginModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginModel loginModel = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                          loginModel.getUsername(),
                          loginModel.getPassword(),
                          new ArrayList<>()
                    )
            );
        } catch (IOException ex) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()

    }
}
