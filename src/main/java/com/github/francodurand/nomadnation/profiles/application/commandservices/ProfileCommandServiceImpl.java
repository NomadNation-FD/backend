package com.github.francodurand.nomadnation.profiles.application.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.profiles.domain.model.aggregates.Profile;
import com.github.francodurand.nomadnation.profiles.domain.model.commands.CreateProfileCommand;
import com.github.francodurand.nomadnation.profiles.domain.model.commands.UpdateNameByIdCommand;
import com.github.francodurand.nomadnation.profiles.domain.services.ProfileCommandService;
import com.github.francodurand.nomadnation.profiles.infrastructure.persistence.mongodb.repositories.ProfileRepository;
import com.github.francodurand.nomadnation.shared.application.outboundservices.cloud.FileStorageService;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FileStorageService fileStorageService;

    private final String folderName = "profiles";

    @Override
    public void handle(CreateProfileCommand command) {
        try {
            var url = fileStorageService.uploadFile(
                    command.userId(),
                    folderName,
                    command.profilePictureBytes());

            var commandWithUrl = new CreateProfileCommand(
                    command.name(),
                    command.userId(),
                    url,
                    null);

            var profile = new Profile(commandWithUrl);

            profileRepository.save(profile);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void handle(UpdateNameByIdCommand command) {
        var profile = profileRepository.findByUserId(command.userId());

        if (profile.isEmpty())
            throw new RuntimeException("Profile not found");

        profile.get().setName(command.name());

        profileRepository.save(profile.get());
    }

}
