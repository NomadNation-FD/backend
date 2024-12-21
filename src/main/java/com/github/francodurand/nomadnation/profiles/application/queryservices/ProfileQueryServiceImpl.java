package com.github.francodurand.nomadnation.profiles.application.queryservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.profiles.domain.model.aggregates.Profile;
import com.github.francodurand.nomadnation.profiles.domain.model.queries.GetProfileByIdQuery;
import com.github.francodurand.nomadnation.profiles.domain.services.ProfileQueryService;
import com.github.francodurand.nomadnation.profiles.infrastructure.persistence.mongodb.repositories.ProfileRepository;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        var profile = profileRepository.findByUserId(query.userId());

        if (profile.isEmpty())
            throw new RuntimeException("Profile not found");

        return profile;
    }

}
