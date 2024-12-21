package com.github.francodurand.nomadnation.reviews.interfaces.rest.resources;

import java.time.LocalDate;

public record CommentResource(
        String userId,
        String name,
        String profilePicture,
        String comment,
        LocalDate createdAt) {
}