package com.github.francodurand.nomadnation.iam.infrastructure.persistence.mongodb.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.francodurand.nomadnation.iam.domain.model.aggregates.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);
}
