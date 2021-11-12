package com.backend.futbol.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class Autorizacion implements Filter {
    
    public static final String KEY="hssbjbsakdba";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        String url= httpServletRequest.getRequestURI();
        if(url.contains("/api/usuarios/login") || url.contains("/api/usuarios") || url.contains("index") || url.contains(".js") || url.contains("ico") || url.contains("css") || url.contains("assets") || url.contains("#")){
            chain.doFilter(request, response);
        }else{
            String hash = httpServletRequest.getHeader("Authorization");
            if(hash==null || hash.trim().equals("")){
                response.setContentType("application/json");
                String body= "{\"autorizacion \": \"NO\"}";
                response.getWriter().write(body);
            }
            try {
                Jws<Claims> claims= Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                if(url.contains("/api/equipos") || url.contains("/api/partidos")){
                    chain.doFilter(request, response);
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        

        
    }
    
}
