package com.github.francodurand.nomadnation.reviews.domain.services;

import java.util.List;

import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;
import com.github.francodurand.nomadnation.reviews.domain.model.queries.GetAllReviewsByOfferIdQuery;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsByOfferIdQuery query);
}
