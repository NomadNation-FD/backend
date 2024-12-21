package com.github.francodurand.nomadnation.profiles.interfaces.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.profiles.domain.model.commands.CreateProfileCommand;
import com.github.francodurand.nomadnation.profiles.domain.model.queries.GetProfileByIdQuery;
import com.github.francodurand.nomadnation.profiles.domain.services.ProfileCommandService;
import com.github.francodurand.nomadnation.profiles.domain.services.ProfileQueryService;

@Service
public class ProfilesContextFacade {
    @Autowired
    private ProfileCommandService profileCommandService;

    @Autowired
    private ProfileQueryService profileQueryService;

    public void createProfile(String name, String userId, byte[] profilePicture) {
        var command = new CreateProfileCommand(name, userId, "default", profilePicture);
        profileCommandService.handle(command);
    }

    public String getNameById(String userId) {
        var query = new GetProfileByIdQuery(userId);

        var profile = profileQueryService.handle(query);

        if (profile.isEmpty())
            throw new RuntimeException("Profile not found");

        return profile.get().getName();
    }

    public String getProfilePictureById(String userId) {
        var query = new GetProfileByIdQuery(userId);

        var profile = profileQueryService.handle(query);

        if (profile.isEmpty())
            throw new RuntimeException("Profile not found");

        return profile.get().getProfilePicture();
    }
}
