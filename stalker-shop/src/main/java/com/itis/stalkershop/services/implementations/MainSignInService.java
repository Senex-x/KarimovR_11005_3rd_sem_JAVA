package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserAuth;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.PasswordService;
import com.itis.stalkershop.services.interfaces.SignInService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;

import java.security.Key;
import java.util.Map;

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

    public UserDto signIn(String token) {
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setClaims(Map.of("user", "Joe")).signWith(key).compact();

        try {
            String userName = (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().get("user");
            //OK, we can trust this JWT
        } catch (JwtException e) {
            //don't trust the JWT!
        }

        throw new RuntimeException(); // Not yet implemented
    }
}
