package com.github.francodurand.nomadnation.profiles.domain.services;

import com.github.francodurand.nomadnation.profiles.domain.model.commands.CreateProfileCommand;
import com.github.francodurand.nomadnation.profiles.domain.model.commands.UpdateNameByIdCommand;

public interface ProfileCommandService {
    void handle(CreateProfileCommand command);

    void handle(UpdateNameByIdCommand command);
}
