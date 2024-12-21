package com.github.francodurand.nomadnation.reviews.domain.model.commands;

import java.util.List;

public record CreateReviewCommand(
        String offerId,
        String authorId,
        String authorName,
        String authorProfilePicture,
        String title,
        String post,
        List<String> media,
        List<byte[]> mediaBytes) {

}
