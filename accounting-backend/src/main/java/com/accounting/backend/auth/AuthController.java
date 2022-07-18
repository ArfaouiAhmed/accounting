package com.accounting.backend.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

/**
 * EXAMPLE:
 * --------------------------
 *
 * - Access current principal in GraphQL handler functions by using the AuthenticationPrincipal annotation
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Controller
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @QueryMapping
    public User me(@AuthenticationPrincipal User user) {
        return user;
    }

    @QueryMapping
    public String ping() { return "pong"; }
}
