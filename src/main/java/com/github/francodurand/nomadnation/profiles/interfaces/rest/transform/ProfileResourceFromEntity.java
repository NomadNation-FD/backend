package com.github.francodurand.nomadnation.profiles.interfaces.rest.transform;

import com.github.francodurand.nomadnation.profiles.domain.model.aggregates.Profile;
import com.github.francodurand.nomadnation.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntity {
    public static ProfileResource fromEntity(Profile entity) {
        return new ProfileResource(entity.getName(), entity.getProfilePicture());
    }

}
