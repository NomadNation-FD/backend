package com.github.francodurand.nomadnation.reviews.interfaces.rest.transform;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.github.francodurand.nomadnation.reviews.domain.model.commands.CreateReviewCommand;
import com.github.francodurand.nomadnation.reviews.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResource {
    public static CreateReviewCommand fromResource(CreateReviewResource resource, String authorId) throws IOException {
        var media = new ArrayList<byte[]>();

        for (MultipartFile image : resource.images()) {
            media.add(image.getBytes());
        }

        return new CreateReviewCommand(
                resource.offerId(),
                authorId,
                "",
                "",
                resource.title(),
                resource.post(),
                null,
                media);
    }
}
