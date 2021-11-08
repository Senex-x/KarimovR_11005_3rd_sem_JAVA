package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserDto

interface UserService {
    fun update(user: UserDto)

    fun deleteImage(user: UserDto)
}