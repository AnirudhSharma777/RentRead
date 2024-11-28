package com.example.readRent.Config;


import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.readRent.Services.JwtService;
import com.example.readRent.Services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private UserDetailsServiceImpl userDetailsService;
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
        @NotNull HttpServletRequest request, 
        @NotNull HttpServletResponse response, 
        @NotNull FilterChain filterChain)
            throws ServletException, IOException {
                if(request.getServletPath().contains("/api/v1/auth")){
                    filterChain.doFilter(request, response);
                    return;
                }

                final String authHeader = request.getHeader("Authorization");
                final String jwt;
                final String userEmail;
                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }

                jwt = authHeader.substring(7);
                userEmail = jwtService.extractUsername(jwt);

                if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                    if (jwtService.isTokenValid(jwt, userDetails)){
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                        );
                        authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                    filterChain.doFilter(request, response);

                }
    }

}
