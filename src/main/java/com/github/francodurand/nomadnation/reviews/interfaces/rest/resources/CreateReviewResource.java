package com.github.francodurand.nomadnation.reviews.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record CreateReviewResource(String offerId, String title, String post, MultipartFile[] images) {
    public CreateReviewResource {
        if (offerId == null || offerId.isBlank())
            throw new RuntimeException("Offer ID is required");

        if (title == null || title.isBlank())
            throw new RuntimeException("Title is required");

        if (post == null || post.isBlank())
            throw new RuntimeException("Post is required");
    }
}
