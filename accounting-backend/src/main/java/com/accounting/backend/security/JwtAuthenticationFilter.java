package com.accounting.backend.security;

import com.accounting.backend.auth.User;
import com.accounting.backend.auth.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Parses JWT Token from Request Header and authenticated the user
 *
 * - if no authentication header is set, no authentication is done
 * - if the given jwt token is either invalid (bad format for example) or expired, authentication is denied
 *
 * Note that this is an example only. DO NOT IMPLEMENT OWN SECURITY CODE IN REAL PRODUCTION APPS !!!!!!!!!!
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            authenticateIfNeeded(request);
        } catch (AuthenticationException bed) {
            logger.error("Could not authenticate: " + bed, bed);
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateIfNeeded(HttpServletRequest request) {
        final String token = getJwtFromRequest(request);
        if (token != null) {
            if (!jwtTokenService.isValidToken(token)) {
                throw new BadCredentialsException("Invalid authorization token");
            }

            String username = jwtTokenService.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("Invalid User in Token"));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String tokenParameter = request.getParameter("token");
        if (tokenParameter != null) {
            return tokenParameter;
        }



        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        if (!authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException(
                    "Invalid 'Authorization'-Header");
        }
        return authHeader.substring(7, authHeader.length());
    }

}


