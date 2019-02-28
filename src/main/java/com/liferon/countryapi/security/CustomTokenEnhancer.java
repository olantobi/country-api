/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferon.countryapi.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    //@Autowired
    //private UserService userService;
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication auth) {
        
        //Collection<? extends GrantedAuthority> auths = auth.getUserAuthentication().getAuthorities();
        //Set<String> authStrings = Collections.unmodifiableSet(auths.stream().map(r -> r.getAuthority()).collect(Collectors.toSet()));
        String username = auth.getName();        
        //User user = userService.findByUsername(username).get();
        
        Map<String, Object> additionalInfo = new HashMap<>();                
        //additionalInfo.put("cooperativeId", (user.getTenantId() == null) ? "" : user.getTenantId());
        //additionalInfo.put("user", new TokenUserDto(user));
        additionalInfo.put("user", username);
        
        ((DefaultOAuth2AccessToken) token).setAdditionalInformation(additionalInfo);
        //((DefaultOAuth2AccessToken)token).setScope(authStrings);
        return token;
    }
    
}
