package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.services.interfaces.PasswordEncoder;
import org.jetbrains.annotations.NotNull;

public class PasswordEncoderMain implements PasswordEncoder {
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
