package com.itis.stalkershop.services.implementations;


import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.SignInServiceBase;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

public class SignInService implements SignInServiceBase {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInService(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @NotNull
    @Override
    public UserDto signIn(UserDto userForm) {
        User user = usersRepository.findByEmail(userForm.getEmail())
                .orElseThrow(() ->
                        new ValidationException(ErrorEntity.NOT_FOUND)
                );
        if (!passwordEncoder.matches(userForm.getPasswordHash(), user.getPasswordHash())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
        return user.toDto();
    }
}
