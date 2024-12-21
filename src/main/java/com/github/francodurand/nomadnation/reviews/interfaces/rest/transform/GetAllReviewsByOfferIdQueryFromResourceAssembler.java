package com.github.francodurand.nomadnation.reviews.interfaces.rest.transform;

import com.github.francodurand.nomadnation.reviews.domain.model.queries.GetAllReviewsByOfferIdQuery;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.GetAllReviewsByOfferIdResource;

public class GetAllReviewsByOfferIdQueryFromResourceAssembler {
    public static GetAllReviewsByOfferIdQuery fromResource(GetAllReviewsByOfferIdResource resource) {
        return new GetAllReviewsByOfferIdQuery(resource.offerId());
    }
}
