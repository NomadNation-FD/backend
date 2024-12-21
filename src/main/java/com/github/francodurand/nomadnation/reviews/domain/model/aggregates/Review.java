package com.github.francodurand.nomadnation.reviews.domain.model.aggregates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.github.francodurand.nomadnation.reviews.domain.model.commands.AddCommentCommand;
import com.github.francodurand.nomadnation.reviews.domain.model.commands.CreateReviewCommand;
import com.github.francodurand.nomadnation.reviews.domain.model.entities.Author;
import com.github.francodurand.nomadnation.reviews.domain.model.entities.Comment;
import com.github.francodurand.nomadnation.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document("reviews")
@NoArgsConstructor
@Getter
public class Review extends AuditableAbstractAggregateRoot<Review> {
    @NotBlank
    private String offerId;

    @NotNull
    private Author author;

    @NotBlank
    private String title;

    @NotBlank
    private String post;

    @NotEmpty
    private List<String> media;

    private List<Comment> comments;

    public Review(CreateReviewCommand command) {
        offerId = command.offerId();
        author = new Author(command.authorId(), command.authorName(), command.authorProfilePicture());
        title = command.title();
        post = command.post();
        media = command.media();
        comments = new ArrayList<>();
    }

    public void addComment(AddCommentCommand command) {
        Comment comment = new Comment(command);
        comments.add(comment);
    }
}
