package com.github.francodurand.nomadnation.reviews.domain.model.commands;

public record AddCommentCommand(
        String reviewId,
        String userId,
        String name,
        String profilePicture,
        String comment) {

}
