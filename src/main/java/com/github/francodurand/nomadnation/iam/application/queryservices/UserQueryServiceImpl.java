package com.github.francodurand.nomadnation.iam.application.queryservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.iam.domain.model.aggregates.User;
import com.github.francodurand.nomadnation.iam.domain.model.queries.GetUserByIdQuery;
import com.github.francodurand.nomadnation.iam.domain.services.UserQueryService;
import com.github.francodurand.nomadnation.iam.infrastructure.persistence.mongodb.repositories.UserRepository;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        var user = userRepository.findById(query.userId());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        return user;
    }
}
