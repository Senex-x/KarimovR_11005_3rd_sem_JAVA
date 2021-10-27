package com.itis.stalkershop.services.implementations;


import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.ValidationService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;

import java.util.Optional;

public class ValidationServiceMain implements ValidationService {
    private final UsersRepository usersRepository;

    public ValidationServiceMain(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // TODO: inspect conditions correctness
    @Override
    public Optional<ErrorEntity> validateRegistration(
            UserRegister form
    ) {
        if(form.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(usersRepository.findByEmail(form.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
