package com.github.francodurand.nomadnation.reviews.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.francodurand.nomadnation.reviews.domain.services.ReviewCommandService;
import com.github.francodurand.nomadnation.reviews.domain.services.ReviewQueryService;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.AddCommentResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.CreateReviewResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.GetAllReviewsByOfferIdResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.ReviewResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.transform.AddCommentCommandFromResourceAssembler;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.transform.CreateReviewCommandFromResource;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.transform.GetAllReviewsByOfferIdQueryFromResourceAssembler;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.transform.ReviewResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reviews")
@Tag(name = "Reviews", description = "Endpoints for managing reviews")
public class ReviewsController {
    @Autowired
    private ReviewCommandService reviewCommandService;

    @Autowired
    private ReviewQueryService reviewQueryService;

    @Autowired
    private HttpServletRequest request;

    @Operation(summary = "Create a new review", description = "Creates a new review with the provided details and images.", responses = {
            @ApiResponse(responseCode = "200", description = "Review created successfully")
    })
    @PostMapping(value = "/create", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> createReview(@ModelAttribute CreateReviewResource resource) {
        try {
            if (resource.images().length == 0) {
                throw new RuntimeException("At least one image is required");
            }

            var authorId = ((String) request.getAttribute("userId"));

            var command = CreateReviewCommandFromResource.fromResource(resource, authorId);

            reviewCommandService.handle(command);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/by-offer-id")
    @Operation(summary = "Get all reviews by offer ID", description = "etrieves all reviews associated with a specific offer ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully", content = @Content(schema = @Schema(implementation = ReviewResource[].class)))
    })
    public ResponseEntity<Object> getAllReviewsByOfferId(@RequestBody GetAllReviewsByOfferIdResource resource) {
        try {
            var query = GetAllReviewsByOfferIdQueryFromResourceAssembler.fromResource(resource);

            var reviews = reviewQueryService.handle(query);

            var reviewsResources = reviews.stream().map(ReviewResourceFromEntityAssembler::fromEntity);

            return ResponseEntity.ok(reviewsResources);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));

        }
    }

    @PostMapping("/add-comment")
    @Operation(summary = "Add a comment to a review", description = "Adds a new comment to an existing review.", responses = {
            @ApiResponse(responseCode = "200", description = "Comment added successfully")
    })
    public ResponseEntity<Object> addComment(@RequestBody AddCommentResource resource) {
        try {
            var authorId = ((String) request.getAttribute("userId"));

            var command = AddCommentCommandFromResourceAssembler.fromResource(resource, authorId);

            reviewCommandService.handle(command);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}