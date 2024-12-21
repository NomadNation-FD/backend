package com.github.francodurand.nomadnation.iam.domain.model.aggregates;

import org.springframework.data.mongodb.core.mapping.Document;

import com.github.francodurand.nomadnation.iam.domain.model.commands.SignUpCommand;
import com.github.francodurand.nomadnation.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Document(collection = "users")
@Getter
public class User extends AuditableAbstractAggregateRoot<User> {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public User() {
    }

    public User(SignUpCommand command) {
        this.email = command.email();
        this.password = command.password();
    }
}
