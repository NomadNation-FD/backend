package com.github.francodurand.nomadnation.iam.infrastructure.tokens;

import com.github.francodurand.nomadnation.iam.application.outboundservices.tokens.TokenService;

import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest token);

    // HACK: investigar
    // String generateToken(Authentication authentication);
}
