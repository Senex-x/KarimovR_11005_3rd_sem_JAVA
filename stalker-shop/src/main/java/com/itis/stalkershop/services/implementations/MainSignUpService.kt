package com.itis.stalkershop.services.implementations

import com.itis.stalkershop.models.Token
import com.itis.stalkershop.models.UserDto
import com.itis.stalkershop.models.UserRegister
import com.itis.stalkershop.repositories.interfaces.UsersRepository
import com.itis.stalkershop.services.interfaces.PasswordService
import com.itis.stalkershop.services.interfaces.SignUpService
import com.itis.stalkershop.services.interfaces.TokenService
import com.itis.stalkershop.services.interfaces.ValidationService
import com.itis.stalkershop.utils.exceptions.ValidationException
import java.util.*

class MainSignUpService(
    private val usersRepository: UsersRepository,
    private val passwordService: PasswordService,
    private val validationService: ValidationService,
    private val tokenService: TokenService,
) : SignUpService {
    override fun signUp(user: UserRegister) {
        val optionalError = validationService.validateRegistration(user)
        if (optionalError.isPresent) {
            throw ValidationException(optionalError.get())
        }

        val newUser = UserDto(
            user.email,
            user.name,
            passwordService.encode(user.password),
            null
        )

        usersRepository.save(newUser)

        val token = UUID.randomUUID().toString()
        // If user had switched to a freshly created account
        // and have valid token linked to his previous account
        tokenService.deleteByToken(token)
        // After remove simply add a new one
        tokenService.add(Token(
            newUser.email,
            token
        ))
    }
}