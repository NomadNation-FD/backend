package com.github.francodurand.nomadnation.reviews.interfaces.rest.resources;

public record AuthorResource(
        String userId,
        String name,
        String profilePicture) {
}