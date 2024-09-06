package com.math.tutor.hub.user_management_service.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.math.tutor.hub.user_management_service.exception.UserNotFoundException;
import com.math.tutor.hub.user_management_service.jwt.JwtService;
import com.math.tutor.hub.user_management_service.response.ErrorResponse;
import com.math.tutor.hub.user_management_service.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

       try {
           final String authHeader = request.getHeader("Authorization");
           final String jwt;
           final String userEmail;

           if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer")){
               filterChain.doFilter(request, response);
               return;
           }
           jwt = authHeader.substring(7);
           userEmail = jwtService.extractUsername(jwt);
           if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
               UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
               if (jwtService.isTokenValid(jwt, userDetails)){
                   SecurityContext context = SecurityContextHolder.createEmptyContext();
                   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                           userDetails, null, userDetails.getAuthorities());
                   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   context.setAuthentication(authToken);
                   SecurityContextHolder.setContext(context);
               }
           }
           filterChain.doFilter(request, response);

       } catch (UserNotFoundException ex){
           response.setStatus(HttpServletResponse.SC_NOT_FOUND);
           response.setContentType("application/json");
           ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());

           ObjectMapper objectMapper = new ObjectMapper();
           String jsonResponse = objectMapper.writeValueAsString(errorResponse);

           // Write the JSON response
           response.getWriter().write(jsonResponse);
           response.getWriter().flush(); // Ensure that the response is sent to the client from the buffer.
           // (Good practise to do when sending large streaming data and so on)

       } catch (Exception ex) {
           // Handle generic exceptions
           response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
           response.setContentType("application/json");

           // Create a generic error response
           ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                   "An internal error occurred");

           // Convert to JSON and write
           ObjectMapper objectMapper = new ObjectMapper();
           String jsonResponse = objectMapper.writeValueAsString(errorResponse);

           response.getWriter().write(jsonResponse);
           response.getWriter().flush();
       }
    }
}
