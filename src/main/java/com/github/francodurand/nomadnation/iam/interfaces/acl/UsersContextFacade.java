package com.github.francodurand.nomadnation.iam.interfaces.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.iam.domain.model.queries.GetUserByIdQuery;
import com.github.francodurand.nomadnation.iam.domain.services.UserQueryService;

@Service
public class UsersContextFacade {
    @Autowired
    private UserQueryService userQueryService;

    public String getEmailById(String userId) {
        var query = new GetUserByIdQuery(userId);

        var user = userQueryService.handle(query);

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        return user.get().getEmail();
    }
}