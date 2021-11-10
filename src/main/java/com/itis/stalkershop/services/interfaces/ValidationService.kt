package com.itis.stalkershop.services.interfaces;

import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;

import java.util.Optional;

interface ValidationService {
    // Why it returns an optional error??
    // Wouldn't it be better if it would just throw an exception instead?
    fun validateRegistration(newUser: UserRegister): Optional<ErrorEntity>
}
