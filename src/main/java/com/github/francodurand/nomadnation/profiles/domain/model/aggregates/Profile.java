package com.github.francodurand.nomadnation.profiles.domain.model.aggregates;

import org.springframework.data.mongodb.core.mapping.Document;

import com.github.francodurand.nomadnation.profiles.domain.model.commands.CreateProfileCommand;
import com.github.francodurand.nomadnation.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("profiles")
@NoArgsConstructor
@Getter
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @NotBlank
    @Setter
    private String name;

    @NotBlank
    private String userId;

    @NotBlank
    private String profilePicture;

    public Profile(CreateProfileCommand command) {
        this.name = command.name();
        this.userId = command.userId();
        this.profilePicture = command.profilePicture();
    }
}
