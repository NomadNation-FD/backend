package com.github.francodurand.nomadnation.profiles.infrastructure.persistence.mongodb.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.francodurand.nomadnation.profiles.domain.model.aggregates.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    public Optional<Profile> findByUserId(String userId);
}
