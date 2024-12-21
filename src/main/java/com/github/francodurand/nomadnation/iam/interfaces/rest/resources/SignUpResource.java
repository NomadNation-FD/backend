package com.github.francodurand.nomadnation.iam.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record SignUpResource(String email, String password, String name, MultipartFile profilePicture) {
    public SignUpResource {
        if (email == null || email.isBlank())
            throw new RuntimeException("Email is required");

        if (password == null || password.isBlank())
            throw new RuntimeException("Password is required");

        if (name == null || name.isBlank())
            throw new RuntimeException("Name is required");
    }
}
