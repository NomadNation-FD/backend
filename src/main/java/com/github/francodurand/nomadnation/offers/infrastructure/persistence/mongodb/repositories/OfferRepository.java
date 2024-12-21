package com.github.francodurand.nomadnation.offers.infrastructure.persistence.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.francodurand.nomadnation.offers.domain.model.aggregates.Offer;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

}
