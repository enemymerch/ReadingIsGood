package com.mcan.rig.security.authenticatoin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mcan.rig.properties.JwtProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationService {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtProperties jwtProperties;

    public UserAuthenticationService(CustomUserDetailsService userDetailsService, JwtProperties jwtProperties) {
        this.customUserDetailsService = userDetailsService;
        this.jwtProperties = jwtProperties;
    }

    public String authenticateAndGenerateToken (String username, String password) throws AuthenticationException {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (userDetails != null && userDetails.getPassword().equals(password)) {
                return generateToken(userDetails);
            } else {
                throw new RuntimeException("INVALID_CREDENTIALS");
            }
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }

    public String generateToken (UserDetails userDetails) {
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return JWT.create().withSubject(userDetails.getUsername()).withIssuedAt(new Date(System.currentTimeMillis()))
           .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getJtwTokenValidity() * 1000))
                .withArrayClaim("roles", authorities.toArray(new String[authorities.size()]))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }
}
