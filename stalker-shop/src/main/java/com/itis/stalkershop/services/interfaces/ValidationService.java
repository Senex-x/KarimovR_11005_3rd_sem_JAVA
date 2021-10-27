package com.itis.stalkershop.services.interfaces;

import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;

import java.util.Optional;

public interface ValidationService {
    Optional<ErrorEntity> validateRegistration(UserRegister newUser);
}
