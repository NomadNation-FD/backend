package com.github.francodurand.nomadnation.iam.domain.services;

import java.util.Optional;

import com.github.francodurand.nomadnation.iam.domain.model.aggregates.User;
import com.github.francodurand.nomadnation.iam.domain.model.queries.GetUserByIdQuery;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
}
