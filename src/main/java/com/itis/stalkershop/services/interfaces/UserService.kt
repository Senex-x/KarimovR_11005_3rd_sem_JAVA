package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserDto
import javax.servlet.http.HttpServletRequest

interface UserService {
    fun update(user: UserDto)

    fun deleteImage(request: HttpServletRequest, user: UserDto)
}