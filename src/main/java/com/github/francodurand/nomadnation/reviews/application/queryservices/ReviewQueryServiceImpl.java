package com.github.francodurand.nomadnation.reviews.application.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;
import com.github.francodurand.nomadnation.reviews.domain.model.queries.GetAllReviewsByOfferIdQuery;
import com.github.francodurand.nomadnation.reviews.domain.services.ReviewQueryService;
import com.github.francodurand.nomadnation.reviews.infrastructure.persistence.mongodb.repositories.ReviewRepository;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> handle(GetAllReviewsByOfferIdQuery query) {
        return reviewRepository.findAllByOfferId(query.offerId());
    }

}
