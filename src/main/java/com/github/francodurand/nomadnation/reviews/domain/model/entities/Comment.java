package com.github.francodurand.nomadnation.reviews.domain.model.entities;

import java.util.Date;

import com.github.francodurand.nomadnation.reviews.domain.model.commands.AddCommentCommand;
import com.github.francodurand.nomadnation.shared.domain.model.entities.AuditableModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment extends AuditableModel {
    private String userId;
    private String name;
    private String profilePicture;
    private String comment;

    public Comment(AddCommentCommand command) {
        userId = command.userId();
        name = command.name();
        profilePicture = command.profilePicture();
        comment = command.comment();
        var now = new Date();
        createdAt = now;
        updatedAt = now;
    }
}
