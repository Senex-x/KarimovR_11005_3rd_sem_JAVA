package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.PasswordService;
import com.itis.stalkershop.services.interfaces.SignUpService;
import com.itis.stalkershop.services.interfaces.ValidationService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SignUpServiceMain implements SignUpService {
    private final UsersRepository usersRepository;
    private final PasswordService passwordService;
    private final ValidationService validationService;

    public SignUpServiceMain(
            UsersRepository usersRepository,
            PasswordService passwordService,
            ValidationService validationService
    ) {
        this.usersRepository = usersRepository;
        this.passwordService = passwordService;
        this.validationService = validationService;
    }

    @Override
    public void signUp(@NotNull UserRegister user) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError =
                validationService.validateRegistration(user);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }

        UserDto newUser = new UserDto(
                user.getEmail(),
                user.getName(),
                passwordService.encode(user.getPassword()),
                null
        );

        usersRepository.save(newUser);

        //TODO: save a mew user in repository
    }
}
