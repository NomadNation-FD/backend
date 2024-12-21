package com.github.francodurand.nomadnation.reviews.domain.services;

import com.github.francodurand.nomadnation.reviews.domain.model.commands.AddCommentCommand;
import com.github.francodurand.nomadnation.reviews.domain.model.commands.CreateReviewCommand;

public interface ReviewCommandService {
    void handle(CreateReviewCommand command);

    void handle(AddCommentCommand command);
}
