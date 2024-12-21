package com.github.francodurand.nomadnation.offers.interfaces.rest.transform;

import com.github.francodurand.nomadnation.offers.domain.model.aggregates.Offer;
import com.github.francodurand.nomadnation.offers.interfaces.rest.resources.OfferResource;

public class OfferResourceFromEntity {
    public static OfferResource fromEntity(Offer entity) {
        return new OfferResource(
                entity.getId(),
                entity.getDestination(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImages());
    }
}
