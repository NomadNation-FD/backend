package com.github.francodurand.nomadnation.offers.interfaces.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.francodurand.nomadnation.offers.domain.model.queries.GetAllOffersQuery;
import com.github.francodurand.nomadnation.offers.domain.model.queries.GetOfferByIdQuery;
import com.github.francodurand.nomadnation.offers.domain.services.OffersQueryService;
import com.github.francodurand.nomadnation.offers.interfaces.rest.resources.OfferResource;
import com.github.francodurand.nomadnation.offers.interfaces.rest.transform.OfferResourceFromEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/offers")
@Tag(name = "Offers", description = "Endpoints for managing offers")
public class OffersController {
    @Autowired
    private OffersQueryService offersQueryService;

    @GetMapping("/all")
    @Operation(summary = "Retrieve all offers", description = "Fetches a list of all available offers.", responses = {
            @ApiResponse(responseCode = "200", description = "Offers retrieved successfully", content = @Content(schema = @Schema(implementation = OfferResource[].class)))
    })
    public ResponseEntity<List<OfferResource>> getOffers() {
        var query = new GetAllOffersQuery();

        var offers = offersQueryService.handle(query);

        var offersResource = offers.stream()
                .map(OfferResourceFromEntity::fromEntity)
                .toList();

        return ResponseEntity.ok(offersResource);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve an offer", description = "Fetches an offer by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Offer retrieved successfully", content = @Content(schema = @Schema(implementation = OfferResource.class)))
    })
    public ResponseEntity<Object> getOffer(@PathVariable("id") String offerId) {
        var query = new GetOfferByIdQuery(offerId);

        var offer = offersQueryService.handle(query);

        if (offer.isEmpty())
            return ResponseEntity.notFound().build();

        var offerResource = OfferResourceFromEntity.fromEntity(offer.get());

        return ResponseEntity.ok(offerResource);
    }
}
