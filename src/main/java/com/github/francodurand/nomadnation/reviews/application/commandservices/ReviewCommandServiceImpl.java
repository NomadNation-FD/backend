package com.github.francodurand.nomadnation.reviews.application.commandservices;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.reviews.application.outboundservices.acl.ExternalProfileService;
import com.github.francodurand.nomadnation.reviews.application.outboundservices.acl.ExternalUserService;
import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;
import com.github.francodurand.nomadnation.reviews.domain.model.commands.AddCommentCommand;
import com.github.francodurand.nomadnation.reviews.domain.model.commands.CreateReviewCommand;
import com.github.francodurand.nomadnation.reviews.domain.services.ReviewCommandService;
import com.github.francodurand.nomadnation.reviews.infrastructure.persistence.mongodb.repositories.ReviewRepository;
import com.github.francodurand.nomadnation.shared.application.outboundservices.cloud.FileStorageService;
import com.github.francodurand.nomadnation.shared.application.outboundservices.email.EmailService;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    @Qualifier("externalProfileServiceReviews")
    private ExternalProfileService externalProfileService;

    @Autowired
    private ExternalUserService externalUserService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private EmailService emailService;

    private final String folderName = "reviews";

    @Override
    public void handle(CreateReviewCommand command) {
        try {
            var urls = command.mediaBytes().stream().map(media -> {
                try {
                    return fileStorageService.uploadFile(
                            // unique identifier for the file
                            UUID.randomUUID().toString(),
                            folderName,
                            media);
                } catch (Exception e) {
                    throw new RuntimeException("Error uploading media");
                }
            }).collect(Collectors.toList());

            var commandWithUrls = new CreateReviewCommand(
                    command.offerId(),
                    command.authorId(),
                    externalProfileService.getNameById(command.authorId()),
                    externalProfileService.getProfilePictureById(command.authorId()),
                    command.title(),
                    command.post(),
                    urls,
                    null);

            var review = new Review(commandWithUrls);

            reviewRepository.save(review);

            var userEmail = externalUserService.getEmailById(command.authorId());
            emailService.sendPostEmail(userEmail, review);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void handle(AddCommentCommand command) {
        var review = reviewRepository.findById(command.reviewId());

        if (review.isEmpty())
            throw new RuntimeException("Review not found");

        var commandWithData = new AddCommentCommand(
                command.reviewId(),
                command.userId(),
                externalProfileService.getNameById(command.userId()),
                externalProfileService.getProfilePictureById(command.userId()),
                command.comment());

        review.get().addComment(commandWithData);

        reviewRepository.save(review.get());

        var userEmail = externalUserService.getEmailById(review.get().getAuthor().getUserId());
        emailService.sendCommentEmail(userEmail, review.get());
    }

}
