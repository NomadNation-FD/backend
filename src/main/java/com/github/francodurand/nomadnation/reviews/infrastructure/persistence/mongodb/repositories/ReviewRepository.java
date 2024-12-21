package com.github.francodurand.nomadnation.reviews.infrastructure.persistence.mongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findAllByOfferId(String offerId);
}
