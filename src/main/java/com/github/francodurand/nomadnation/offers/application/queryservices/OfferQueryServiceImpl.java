package com.github.francodurand.nomadnation.offers.application.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.offers.domain.model.aggregates.Offer;
import com.github.francodurand.nomadnation.offers.domain.model.queries.GetAllOffersQuery;
import com.github.francodurand.nomadnation.offers.domain.model.queries.GetOfferByIdQuery;
import com.github.francodurand.nomadnation.offers.domain.services.OffersQueryService;
import com.github.francodurand.nomadnation.offers.infrastructure.persistence.mongodb.repositories.OfferRepository;

@Service
public class OfferQueryServiceImpl implements OffersQueryService {
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> handle(GetAllOffersQuery query) {
        return offerRepository.findAll();
    }

    @Override
    public Optional<Offer> handle(GetOfferByIdQuery query) {
        return offerRepository.findById(query.offerId());
    }

}
