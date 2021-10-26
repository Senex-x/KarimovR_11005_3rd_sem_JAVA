package com.itis.stalkershop.services.implementations;


import com.itis.senex.exception.ErrorEntity;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.senex.repository.dto.UserForm;
import com.itis.senex.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {
    private final UsersRepository usersRepository;

    public ValidatorImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<ErrorEntity> validateRegistration(UserForm form) {
        if(form.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(usersRepository.findByEmail(form.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
