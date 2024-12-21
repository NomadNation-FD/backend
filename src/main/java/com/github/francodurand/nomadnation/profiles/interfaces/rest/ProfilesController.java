package com.github.francodurand.nomadnation.profiles.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.francodurand.nomadnation.profiles.domain.model.queries.GetProfileByIdQuery;
import com.github.francodurand.nomadnation.profiles.domain.services.ProfileCommandService;
import com.github.francodurand.nomadnation.profiles.domain.services.ProfileQueryService;
import com.github.francodurand.nomadnation.profiles.interfaces.rest.resources.UpdateNameResource;
import com.github.francodurand.nomadnation.profiles.interfaces.rest.transform.ProfileResourceFromEntity;
import com.github.francodurand.nomadnation.profiles.interfaces.rest.transform.UpdateNameByIdCommandFromResource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/profiles")
@Tag(name = "Profiles", description = "Endpoints for managing profiles")
public class ProfilesController {
    @Autowired
    private ProfileCommandService profileCommandService;

    @Autowired
    private ProfileQueryService profileQueryService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/update-name")
    @Operation(summary = "Update profile name", description = "Updates the profile name based on the provided resource.", responses = {
            @ApiResponse(responseCode = "200", description = "Name updated successfully")
    })
    public ResponseEntity<Object> updateName(@RequestBody UpdateNameResource resource) {
        var userId = ((String) request.getAttribute("userId"));

        var command = UpdateNameByIdCommandFromResource.fromResource(resource, userId);

        profileCommandService.handle(command);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile-info")
    @Operation(summary = "Get profile info", description = "Get the profile info based on the provided resource.", responses = {
            @ApiResponse(responseCode = "200", description = "Profile info retrieved successfully")
    })
    public ResponseEntity<Object> getProfileInfo() {
        var userId = ((String) request.getAttribute("userId"));

        var query = new GetProfileByIdQuery(userId);

        var profile = profileQueryService.handle(query);

        if (profile.isEmpty())
            return ResponseEntity.notFound().build();

        var profileResource = ProfileResourceFromEntity.fromEntity(profile.get());

        return ResponseEntity.ok(profileResource);
    }
}
