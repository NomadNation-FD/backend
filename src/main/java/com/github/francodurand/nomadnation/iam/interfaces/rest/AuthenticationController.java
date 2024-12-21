package com.github.francodurand.nomadnation.iam.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.francodurand.nomadnation.iam.domain.services.UserCommandService;
import com.github.francodurand.nomadnation.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.github.francodurand.nomadnation.iam.interfaces.rest.resources.SignInResource;
import com.github.francodurand.nomadnation.iam.interfaces.rest.resources.SignUpResource;
import com.github.francodurand.nomadnation.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.github.francodurand.nomadnation.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.github.francodurand.nomadnation.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthenticationController {
    @Autowired
    private UserCommandService userCommandService;

    @Operation(summary = "Register a new user", description = "Creates a new user account with the provided details and profile picture.}", responses = {
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(schema = @Schema(implementation = AuthenticatedUserResource.class)))
    })

    @PostMapping(value = "/sign-up", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> signUp(
            @ModelAttribute SignUpResource resource) {
        try {
            if (resource.profilePicture().isEmpty()) {
                throw new RuntimeException("Profile picture is required");
            }

            var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);

            var user = userCommandService.handle(command);

            var token = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(summary = "Authenticate user", description = "Authenticates the user using their email and password.", responses = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully", content = @Content(schema = @Schema(implementation = AuthenticatedUserResource.class)))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInResource resource) {
        try {
            var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);

            var user = userCommandService.handle(command);

            var token = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}