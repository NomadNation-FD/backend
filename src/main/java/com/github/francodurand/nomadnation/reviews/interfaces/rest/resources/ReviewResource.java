package com.github.francodurand.nomadnation.reviews.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record ReviewResource(
                String id,
                AuthorResource author,
                String title,
                String post,
                List<String> media,
                CommentResource[] comments,
                LocalDate createdAt) {
}
