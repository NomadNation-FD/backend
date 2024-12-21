package com.github.francodurand.nomadnation.iam.application.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.profiles.interfaces.acl.ProfilesContextFacade;

@Service("externalProfileServiceIam")
public class ExternalProfileService {
    @Autowired
    private ProfilesContextFacade profilesContextFacade;

    public void createProfile(String name, String userId, byte[] profilePicture) {
        profilesContextFacade.createProfile(name, userId, profilePicture);
    }
}
