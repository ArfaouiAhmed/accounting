package com.accounting.backend.security;

import com.accounting.backend.auth.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Adds JWT-based Authentication and Authorization to the server
 *
 * Note that this is an example only. DO NOT IMPLEMENT OWN SECURITY CODE IN REAL PRODUCTION APPS !!!!!!!!!!
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // Enable @PreAuthorize method-level security
public class SecurityConfig  {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserRepository userRepository) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                username
                        )
                );
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService);
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // for h2 explorer
        http.headers().frameOptions().sameOrigin();

        // Exception Handling
        http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            log.error("Unauthorized request to '{}'- {}",
                                    request.getRequestURL(),
                                    ex.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                );

        http.authorizeRequests()
                // Allow access to login
                .antMatchers("/login").permitAll()
                // allow access to graphiql
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/s.html").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/graphiql/**").permitAll()
                .antMatchers("/graphql/**").permitAll()

                // ...while all other endpoints (INCLUDING /graphql !) should be authenticated
                //    fine granular, Role-based, access checks are done in the resolver
                .anyRequest().authenticated();

        // Register JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(Environment env) {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        String allowedOrigins = env.getProperty("ACCOUNTING_ALLOWED_ORIGINS", "http://localhost:8080");

        Arrays.stream(allowedOrigins.split(","))
                .forEach(origin -> {
                    log.info("Allowing Cors for host '{}'", origin);
                    config.addAllowedOrigin(origin);
                });

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
