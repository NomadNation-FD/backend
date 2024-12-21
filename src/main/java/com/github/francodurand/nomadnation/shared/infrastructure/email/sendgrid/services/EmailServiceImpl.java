package com.github.francodurand.nomadnation.shared.infrastructure.email.sendgrid.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.reviews.domain.model.aggregates.Review;
import com.github.francodurand.nomadnation.shared.infrastructure.email.sendgrid.SendGridEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import jakarta.annotation.PostConstruct;

@Service
public class EmailServiceImpl implements SendGridEmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private SendGrid sg;
    private final Email from = new Email("u20201f640@upc.edu.pe");
    private final Request request = new Request();

    private final String baseUrl = "https://nomadnation.netlify.app/offers/";

    private static final String REGISTER_TEMPLATE_ID = "d-f89e18a62a1347d293b3b3f77ef445ef";
    private static final String POST_TEMPLATE_ID = "d-301138f4bcf246e1a64e42310e5f6d21";
    private static final String COMMENT_TEMPLATE_ID = "d-73929c07d6dd446eaff6290ae3cd7eff";

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        sg = new SendGrid(apiKey);
    }

    @Override
    public void sendRegisterEmail(String to) {
        sendEmail(to, REGISTER_TEMPLATE_ID, null);
    }

    @Override
    public void sendPostEmail(String to, Review review) {
        var images = review.getMedia();
        int randomIndex = ThreadLocalRandom.current().nextInt(images.size());
        String randomImage = images.get(randomIndex);

        Map<String, String> dynamicData = new HashMap<>();
        dynamicData.put("author_name", review.getAuthor().getName());
        dynamicData.put("image_url", randomImage);
        dynamicData.put("post_url", baseUrl + review.getOfferId());

        sendEmail(to, POST_TEMPLATE_ID, dynamicData);
    }

    @Override
    public void sendCommentEmail(String to, Review review) {
        var lastComment = review.getComments().get(review.getComments().size() - 1);

        Map<String, String> dynamicData = new HashMap<>();
        dynamicData.put("author_name", review.getAuthor().getName());
        dynamicData.put("post_url", baseUrl + review.getOfferId());
        dynamicData.put("post_title", review.getTitle());
        dynamicData.put("comment_author_name", lastComment.getName());

        sendEmail(to, COMMENT_TEMPLATE_ID, dynamicData);
    }

    private void sendEmail(String to, String templateId, Map<String, String> dynamicData) {
        Mail mail = buildEmail(to, templateId, dynamicData);

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            logger.info("Email sent successfully.");
            logger.debug("Status: {}", response.getStatusCode());
            logger.debug("Headers: {}", response.getHeaders());
            logger.debug("Body: {}", response.getBody());
        } catch (IOException ex) {
            logger.error("Error sending email: {}", ex.getMessage(), ex);
        }
    }

    private Mail buildEmail(String to, String templateId, Map<String, String> dynamicData) {
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId);

        Personalization personalization = new Personalization();
        personalization.addTo(new Email(to));

        if (dynamicData != null) {
            dynamicData.forEach(personalization::addDynamicTemplateData);
        }

        mail.addPersonalization(personalization);
        return mail;
    }
}
