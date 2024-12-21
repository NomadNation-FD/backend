package com.github.francodurand.nomadnation.iam.interfaces.rest.transform;

import com.github.francodurand.nomadnation.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(String token) {
        return new AuthenticatedUserResource(token);
    }
}
