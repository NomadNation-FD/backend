package com.github.francodurand.nomadnation.reviews.domain.model.entities;

import com.github.francodurand.nomadnation.shared.domain.model.entities.AuditableModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Author extends AuditableModel {
    private String userId;
    private String name;
    private String profilePicture;
}
