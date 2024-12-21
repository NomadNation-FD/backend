package com.github.francodurand.nomadnation.iam.interfaces.rest.resources;

public record SignInResource(String email, String password) {
    public SignInResource {
        if (email == null || email.isBlank())
            throw new RuntimeException("Email is required");

        if (password == null || password.isBlank())
            throw new RuntimeException("Password is required");
    }
}
