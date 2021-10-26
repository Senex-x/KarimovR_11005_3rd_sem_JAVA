package com.itis.stalkershop.services.implementations;

import com.itis.senex.exception.ErrorEntity;
import com.itis.senex.exception.ValidationException;
import com.itis.senex.model.User;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.senex.repository.dto.UserDto;
import com.itis.senex.repository.dto.UserForm;
import com.itis.senex.services.PasswordEncoder;
import com.itis.senex.services.SignInService;
import org.jetbrains.annotations.NotNull;

public class SignInServiceImpl implements SignInService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceImpl(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @NotNull
    @Override
    public UserDto signIn(UserForm userForm) {
        User user = usersRepository.findByEmail(userForm.getEmail())
                .orElseThrow(() ->
                        new ValidationException(ErrorEntity.NOT_FOUND)
                );
        if (!passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
        return UserDto.Companion.from(user);
    }
}
