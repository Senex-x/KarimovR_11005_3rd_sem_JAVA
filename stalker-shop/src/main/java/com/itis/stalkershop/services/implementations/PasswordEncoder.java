package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.services.interfaces.PasswordEncoderBase;
import org.jetbrains.annotations.NotNull;

public class PasswordEncoder implements PasswordEncoderBase {
    @Override
    public boolean matches(String password, @NotNull String hashPassword) {
        return password.equals(hashPassword);
    }

    @NotNull
    @Override
    public String encode(@NotNull String password) {
        return password;
    }
}
