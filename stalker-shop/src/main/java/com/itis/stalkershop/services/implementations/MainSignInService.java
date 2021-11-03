package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserAuth;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.PasswordService;
import com.itis.stalkershop.services.interfaces.SignInService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

public class MainSignInService implements SignInService {
    private final UsersRepository usersRepository;
    private final PasswordService passwordServiceMain;

    public MainSignInService(
            UsersRepository usersRepository,
            PasswordService passwordServiceMain
    ) {
        this.usersRepository = usersRepository;
        this.passwordServiceMain = passwordServiceMain;
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
        if (!passwordServiceMain.matches(
                userForm.getPassword(),
                user.getPasswordHash()
        )) {
            throw new ValidationException(
                    ErrorEntity.INCORRECT_PASSWORD
            );
        }
        return user.toUserDto();
    }
}
