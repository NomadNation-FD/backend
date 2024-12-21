package com.github.francodurand.nomadnation.reviews.application.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.iam.interfaces.acl.UsersContextFacade;

@Service
public class ExternalUserService {
    @Autowired
    private UsersContextFacade userContextFacade;

    public String getEmailById(String userId) {
        return userContextFacade.getEmailById(userId);
    }
}
