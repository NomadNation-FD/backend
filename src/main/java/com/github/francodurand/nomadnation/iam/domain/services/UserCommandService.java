package com.github.francodurand.nomadnation.iam.domain.services;

import com.github.francodurand.nomadnation.iam.domain.model.commands.SignInCommand;
import com.github.francodurand.nomadnation.iam.domain.model.commands.SignUpCommand;

public interface UserCommandService {
    public String handle(SignUpCommand command);

    public String handle(SignInCommand command);
}
