package com.github.francodurand.nomadnation.iam.interfaces.rest.transform;

import java.io.IOException;

import com.github.francodurand.nomadnation.iam.domain.model.commands.SignUpCommand;
import com.github.francodurand.nomadnation.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) throws IOException {
        return new SignUpCommand(
                resource.email(),
                resource.password(),
                resource.name(),
                resource.profilePicture().getBytes());
    }
}