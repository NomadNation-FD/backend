package com.github.francodurand.nomadnation.reviews.application.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.profiles.interfaces.acl.ProfilesContextFacade;

@Service("externalProfileServiceReviews")
public class ExternalProfileService {
    @Autowired
    private ProfilesContextFacade profilesContextFacade;

    public String getNameById(String userId) {
        return profilesContextFacade.getNameById(userId);
    }

    public String getProfilePictureById(String userId) {
        return profilesContextFacade.getProfilePictureById(userId);
    }

}
