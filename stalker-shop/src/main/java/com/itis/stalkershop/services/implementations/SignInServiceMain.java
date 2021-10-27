package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserAuth;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.PasswordEncoder;
import com.itis.stalkershop.services.interfaces.SignInService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

public class SignInServiceMain implements SignInService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoderMain;

    public SignInServiceMain(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoderMain
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoderMain = passwordEncoderMain;
    }

    @NotNull
    @Override
    public UserDto signIn(UserAuth userForm) {
        User user = usersRepository
                .findByEmail(userForm.getEmail())
                .orElseThrow(() ->
                        new ValidationException(
                                ErrorEntity.NOT_FOUND
                        )
                );
        if (!passwordEncoderMain.matches(
                userForm.getPassword(),
                user.getPasswordHash()
        )) {
            throw new ValidationException(
                    ErrorEntity.INCORRECT_PASSWORD
            );
        }
        return user.toDto();
    }
}
