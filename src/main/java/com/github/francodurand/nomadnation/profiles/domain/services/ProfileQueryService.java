package com.github.francodurand.nomadnation.profiles.domain.services;

import java.util.Optional;

import com.github.francodurand.nomadnation.profiles.domain.model.aggregates.Profile;
import com.github.francodurand.nomadnation.profiles.domain.model.queries.GetProfileByIdQuery;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
}
