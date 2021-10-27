package com.itis.stalkershop.services.implementations;


import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.ValidatorBase;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;

import java.util.Optional;

public class Validator implements ValidatorBase {
    private final UsersRepository usersRepository;

    public Validator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // TODO: fix warning
    @Override
    public Optional<ErrorEntity> validateRegistration(UserDto form) {
        if(form.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(usersRepository.findByEmail(form.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
