package com.github.francodurand.nomadnation.iam.application.outboundservices.hashing;

public interface HashingService {
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
