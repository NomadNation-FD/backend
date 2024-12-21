package com.github.francodurand.nomadnation.iam.interfaces.rest.transform;

import com.github.francodurand.nomadnation.iam.domain.model.commands.SignInCommand;
import com.github.francodurand.nomadnation.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(
                resource.email(),
                resource.password());
    }
}