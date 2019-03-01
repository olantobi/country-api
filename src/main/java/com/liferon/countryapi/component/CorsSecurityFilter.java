package com.liferon.countryapi.component;

import com.liferon.countryapi.AppConstants;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsSecurityFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
        response.addHeader("Access-Control-Expose-Headers", "x-requested-with");

        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);

        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().equals("/oauth/token")) {

            String rawHeaderString = AppConstants.FrontEndClient.CLIENT_ID +":"+AppConstants.FrontEndClient.CLIENT_SECRET;
            String authHeader = Base64.encodeBase64String(rawHeaderString.getBytes());

            mutableRequest.putHeader("Authorization", "Basic "+authHeader);
            mutableRequest.setAttribute("grant_type", "password");

            System.out.println("Authentication: "+authHeader);
        }

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            //response.setStatus(HttpServletResponse.SC_OK);
        } else {
            try {
                filterChain.doFilter(mutableRequest, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
