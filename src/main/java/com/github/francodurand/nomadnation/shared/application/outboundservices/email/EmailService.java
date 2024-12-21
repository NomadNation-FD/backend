package com.github.francodurand.nomadnation.shared.application.outboundservices.email;

import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;

public interface EmailService {
    void sendRegisterEmail(String to);

    void sendPostEmail(String to, Review review);

    void sendCommentEmail(String to, Review review);
}
