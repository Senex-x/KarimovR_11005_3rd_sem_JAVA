package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.SignUpService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SignUpServiceMain implements SignUpService {
    private final UsersRepository usersRepository;
    private final PasswordEncoderMain passwordEncoderMain;
    private final ValidatorMain validatorMain;

    public SignUpServiceMain(
            UsersRepository usersRepository,
            PasswordEncoderMain passwordEncoderMain,
            ValidatorMain validatorMain
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoderMain = passwordEncoderMain;
        this.validatorMain = validatorMain;
    }

    @Override
    public void signUp(@NotNull UserDto user) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError = validatorMain.validateRegistration(user);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        usersRepository.save(user);
    }
}
