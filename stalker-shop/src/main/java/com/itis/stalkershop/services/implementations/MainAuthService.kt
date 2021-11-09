package com.itis.stalkershop.services.implementations

import com.itis.stalkershop.models.Token
import com.itis.stalkershop.models.UserAuth
import com.itis.stalkershop.models.UserDto
import com.itis.stalkershop.repositories.interfaces.UsersRepository
import com.itis.stalkershop.services.interfaces.AuthService
import com.itis.stalkershop.services.interfaces.AuthService.AuthCallback
import com.itis.stalkershop.services.interfaces.PasswordService
import com.itis.stalkershop.services.interfaces.TokenService
import com.itis.stalkershop.utils.exceptions.ErrorEntity
import com.itis.stalkershop.utils.exceptions.ValidationException
import com.itis.stalkershop.utils.log
import com.itis.stalkershop.utils.setSessionUser
import java.security.SignatureException
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class MainAuthService(
    private val usersRepository: UsersRepository,
    private val tokenService: TokenService,
    private val passwordServiceMain: PasswordService
) : AuthService {
    // TODO: refactor
    override fun auth(
        request: HttpServletRequest,
        response: HttpServletResponse,
        userAuth: UserAuth,
        callback: AuthCallback
    ) {
        val user = try {
            usersRepository.findByEmail(userAuth.email).get()
        } catch (exception: NoSuchElementException) {
            callback.onFail(
                ValidationException(ErrorEntity.NOT_FOUND)
            )
            return
        }

        if (!passwordServiceMain.matches(
                userAuth.password,
                user.passwordHash
            )
        ) {
            callback.onFail(
                ValidationException(ErrorEntity.NOT_FOUND)
            )
            return
        }

        val token = UUID.randomUUID().toString()
        response.addCookie(
            Cookie("token", token)
        )
        tokenService.replace(
            Token(user.email, token)
        )

        request.setSessionUser(user.toUserDto())

        callback.onSuccess(user.toUserDto())
    }

    // TODO: refactor
    override fun auth(request: HttpServletRequest, callback: AuthCallback) {
        log("Trying to authenticate user by token")

        val cookie = request.cookies.find { item -> item.name == "token" }

        //val optionalTokenCookie: Optional<Cookie> = Arrays.stream(request.cookies)
        //    .filter { item -> item.name.equals("token") }
        //    .findFirst()

        if (cookie != null) {
            val token = cookie.value

            tokenService.getUserEmail(token)?.let {
                val userDto = usersRepository.findByEmail(it).get().toUserDto()
                request.setSessionUser(userDto)
                callback.onSuccess(
                    userDto
                )
            }
        }

        callback.onFail(
            ValidationException(ErrorEntity.INVALID_TOKEN)
        )
    }
}