package com.itis.stalkershop.services.interfaces;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(UserDto newUser);
}
