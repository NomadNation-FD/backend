package com.github.francodurand.nomadnation.reviews.interfaces.rest.transform;

import com.github.francodurand.nomadnation.reviews.domain.model.commands.AddCommentCommand;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.AddCommentResource;

public class AddCommentCommandFromResourceAssembler {
    public static AddCommentCommand fromResource(AddCommentResource resource, String authorId) {
        return new AddCommentCommand(
                resource.reviewId(),
                authorId,
                null,
                null,
                resource.comment());
    }
}
