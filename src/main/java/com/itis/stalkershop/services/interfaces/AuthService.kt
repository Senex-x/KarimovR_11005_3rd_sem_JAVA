package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserAuth
import com.itis.stalkershop.models.UserDto
import com.itis.stalkershop.utils.exceptions.ValidationException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface AuthService {
    interface AuthCallback {
        fun onSuccess(userDto: UserDto)

        fun onFail(exception: ValidationException)
    }

    fun auth(request: HttpServletRequest, response: HttpServletResponse, userAuth: UserAuth, callback: AuthCallback)

    fun auth(request: HttpServletRequest, callback: AuthCallback)
}