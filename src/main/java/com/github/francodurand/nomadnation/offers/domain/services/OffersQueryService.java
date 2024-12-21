package com.github.francodurand.nomadnation.offers.domain.services;

import java.util.List;
import java.util.Optional;

import com.github.francodurand.nomadnation.offers.domain.model.aggregates.Offer;
import com.github.francodurand.nomadnation.offers.domain.model.queries.GetAllOffersQuery;
import com.github.francodurand.nomadnation.offers.domain.model.queries.GetOfferByIdQuery;

public interface OffersQueryService {
    List<Offer> handle(GetAllOffersQuery query);

    Optional<Offer> handle(GetOfferByIdQuery query);
}
