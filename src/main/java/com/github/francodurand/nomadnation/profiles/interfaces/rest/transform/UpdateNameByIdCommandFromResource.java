package com.github.francodurand.nomadnation.profiles.interfaces.rest.transform;

import com.github.francodurand.nomadnation.profiles.domain.model.commands.UpdateNameByIdCommand;
import com.github.francodurand.nomadnation.profiles.interfaces.rest.resources.UpdateNameResource;

public class UpdateNameByIdCommandFromResource {
    public static UpdateNameByIdCommand fromResource(UpdateNameResource resource, String id) {
        return new UpdateNameByIdCommand(resource.name(), id);
    }
}
