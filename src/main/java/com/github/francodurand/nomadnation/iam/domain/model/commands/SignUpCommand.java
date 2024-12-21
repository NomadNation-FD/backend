package com.github.francodurand.nomadnation.iam.domain.model.commands;

public record SignUpCommand(String email, String password, String name, byte[] profilePicture) {
}
