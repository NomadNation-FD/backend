package com.github.francodurand.nomadnation.reviews.interfaces.rest.transform;

import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.AuthorResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.CommentResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.ReviewResource;

import java.time.LocalDate;
import java.time.ZoneId;

public class ReviewResourceFromEntityAssembler {
        public static ReviewResource fromEntity(Review entity) {
                var author = new AuthorResource(
                                entity.getAuthor().getUserId(),
                                entity.getAuthor().getName(),
                                entity.getAuthor().getProfilePicture());

                var comments = entity.getComments().stream()
                                .map(comment -> new CommentResource(
                                                comment.getUserId(),
                                                comment.getName(),
                                                comment.getProfilePicture(),
                                                comment.getComment(),
                                                comment.getCreatedAt().toInstant().atZone(ZoneId.systemDefault())
                                                                .toLocalDate()))
                                .toArray(CommentResource[]::new);

                LocalDate createdAt = entity.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                return new ReviewResource(
                                entity.getId(),
                                author,
                                entity.getTitle(),
                                entity.getPost(),
                                entity.getMedia(),
                                comments,
                                createdAt);
        }
}
