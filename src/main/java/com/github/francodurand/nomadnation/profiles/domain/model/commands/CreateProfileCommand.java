package com.github.francodurand.nomadnation.profiles.domain.model.commands;

public record CreateProfileCommand(
        String name,
        String userId,
        String profilePicture,
        byte[] profilePictureBytes) {

}
