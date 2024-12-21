package com.github.francodurand.nomadnation.iam.application.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.francodurand.nomadnation.iam.application.outboundservices.acl.ExternalProfileService;
import com.github.francodurand.nomadnation.iam.application.outboundservices.hashing.HashingService;
import com.github.francodurand.nomadnation.iam.application.outboundservices.tokens.TokenService;
import com.github.francodurand.nomadnation.iam.domain.model.aggregates.User;
import com.github.francodurand.nomadnation.iam.domain.model.commands.SignInCommand;
import com.github.francodurand.nomadnation.iam.domain.model.commands.SignUpCommand;
import com.github.francodurand.nomadnation.iam.domain.services.UserCommandService;
import com.github.francodurand.nomadnation.iam.infrastructure.persistence.mongodb.repositories.UserRepository;
import com.github.francodurand.nomadnation.shared.application.outboundservices.email.EmailService;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("externalProfileServiceIam")
    private ExternalProfileService externalProfileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HashingService hashingService;

    @Autowired
    private EmailService emailService;

    @Override
    public String handle(SignUpCommand command) {
        var hashedPassword = hashingService.encode(command.password());
        var user = new User(new SignUpCommand(command.email(), hashedPassword, command.name(), null));

        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email already in use");

        userRepository.save(user);
        externalProfileService.createProfile(command.name(), user.getId(), command.profilePicture());
        emailService.sendRegisterEmail(user.getEmail());

        return tokenService.generateToken(user.getEmail());
    }

    @Override
    public String handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        return tokenService.generateToken(user.get().getEmail());
    }

}
