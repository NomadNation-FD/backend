package com.github.francodurand.nomadnation.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.francodurand.nomadnation.iam.application.outboundservices.hashing.HashingService;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
