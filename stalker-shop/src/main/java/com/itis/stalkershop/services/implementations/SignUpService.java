package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.SignUpServiceBase;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SignUpService implements SignUpServiceBase {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpService(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            Validator validator
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(@NotNull UserDto user) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError = validator.validateRegistration(user);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        usersRepository.save(user);
    }
}
