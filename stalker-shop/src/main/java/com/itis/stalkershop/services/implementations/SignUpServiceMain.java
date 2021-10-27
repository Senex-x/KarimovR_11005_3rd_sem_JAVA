package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.PasswordEncoder;
import com.itis.stalkershop.services.interfaces.SignUpService;
import com.itis.stalkershop.services.interfaces.Validator;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SignUpServiceMain implements SignUpService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpServiceMain(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            Validator validator
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(@NotNull UserRegister user) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError =
                validator.validateRegistration(user);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }

        UserDto newUser = new UserDto(
                user.getEmail(),
                user.getName(),
                passwordEncoder.encode(user.getPassword()),
                null
        );

        usersRepository.save(newUser);

        //TODO: save a mew user in repository
    }
}
