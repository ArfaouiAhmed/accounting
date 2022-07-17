package com.accounting.backend.security;

import com.accounting.backend.auth.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Login via Username/Password with "REST" API.
 * <p>
 * - The /graphql endpoints requires a valid token. This token can be requests by invoking
 * HTTP "POST /login with" sending username and password.
 * <p>
 * Note that this is an example only. DO NOT IMPLEMENT OWN SECURITY CODE IN REAL PRODUCTION APPS !!!!!!!!!!
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Slf4j
@RestController
public class LoginController {

    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenService jwtTokenService;

    public LoginController(AuthenticationProvider authenticationProvider, JwtTokenService jwtTokenService) {
        this.authenticationProvider = authenticationProvider;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            Authentication authenticate = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            if (authenticate != null && (authenticate.getPrincipal() instanceof User)) {
                User principal = (User) authenticate.getPrincipal();
                String jwtToken = jwtTokenService.createTokenForUser(principal);

                return ResponseEntity.ok()
                        .body(new LoginResponse((jwtToken)));

            }
        } catch (AuthenticationException ex) {
            log.error("Authentication failed: " + ex, ex);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}