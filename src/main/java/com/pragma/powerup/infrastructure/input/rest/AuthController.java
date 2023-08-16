package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.security.AuthenticationRequest;
import com.pragma.powerup.security.AuthenticationResponse;
import com.pragma.powerup.security.JWTUtil;
import com.pragma.powerup.security.RegisterUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;


    private final RegisterUserDetailsService registerUserDetailsService;


    @Autowired
    private JWTUtil jwtUtil;


    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated", content = @Content),
    })

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) throws BadCredentialsException {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getClave()));
            UserDetails userDetails = registerUserDetailsService.loadUserByUsername(request.getEmail());
                String jwt = jwtUtil.generateToken(userDetails);return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

    }

}
