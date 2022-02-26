package com.mcan.rig.rest.controller;

import com.mcan.rig.rest.request.AuthenticationRequest;
import com.mcan.rig.rest.response.JwtResponse;
import com.mcan.rig.security.authenticatoin.UserAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/login")
public class AuthenticationController {

    private final UserAuthenticationService userAuthenticationService;

    public AuthenticationController (UserAuthenticationService userAuthenticationService) {this.userAuthenticationService = userAuthenticationService;}

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest) {
        String token = userAuthenticationService.authenticateAndGenerateToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        JwtResponse response = new JwtResponse(token);
        return ResponseEntity.ok(response);
    }
}