/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferon.countryapi.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class MutableHttpServletRequest extends HttpServletRequestWrapper {
    
    // holds custom header and value mapping
    private final Map<String, String> customHeaders;
    private final Map<String, String[]> parameterMap;
    
    public MutableHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<>();
        this.parameterMap = new HashMap<>();
    }
    
    public void putHeader(String name, String value){
        this.customHeaders.put(name, value);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map paramMap = super.getParameterMap();

        parameterMap.putAll(paramMap);

        return parameterMap;
    }

    public void addParameter(String parameterKey, String parameterValue) {
        parameterMap.put(parameterKey, new String[] {parameterValue});
    }

    @Override
    public String getHeader(String name) {
        // check the custom headers first
        String headerValue = customHeaders.get(name);
        
        if (headerValue != null){
            return headerValue;
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }
    
    @Override
    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<>(customHeaders.keySet());
        
        // now add the headers from the wrapped request object
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }
 
        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }
}
