package com.mcan.rig.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.mcan.rig.exception.ErrorResponse;
import com.mcan.rig.persistance.entity.Customer;
import com.mcan.rig.security.authenticatoin.CustomUserDetails;
import com.mcan.rig.service.CustomerService;
import com.mcan.rig.properties.JwtProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final CustomerService customerService;

    private final JwtProperties jwtProperties;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomerService customerService, JwtProperties jwtProperties) {
        super(authenticationManager);
        this.customerService = customerService;
        this.jwtProperties = jwtProperties;
    }


    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            String header = request.getHeader(jwtProperties.getHeader());

            if (header == null || !header.startsWith(jwtProperties.getTokenPrefix())) {
                chain.doFilter(request, response);
                return;
            }

            Authentication authentication = getUsernamePasswordAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                                                  .message(e.getLocalizedMessage())
                                                  .method(request.getMethod())
                                                  .path(request.getRequestURI())
                                                  .timestamp(new Date()).build();

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
        chain.doFilter(request, response);
    }
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private Authentication getUsernamePasswordAuthentication (HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader())
                              .replace(jwtProperties.getTokenPrefix(), "");
        if (token != null) {
            DecodedJWT result = JWT.require(HMAC512(jwtProperties.getSecret()))
                                   .build()
                                   .verify(token);
            List<Map<Object, Object>> tokenValues = new ArrayList<>();

            String username = result.getClaim("sub").as(TextNode.class).asText();
            ArrayNode roleName = result.getClaim("roles").as(ArrayNode.class);

            Map<Object, Object> role = new HashMap<>();

            role.put("role", roleName);
            tokenValues.add(role);

            if (username != null) {
                Customer customer = customerService.findByUsername(username);
                CustomUserDetails customUserDetails = new CustomUserDetails(customer);
                return new UsernamePasswordAuthenticationToken(username, tokenValues, customUserDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }

}
