package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.User
import com.itis.stalkershop.models.UserDto

// Shows if user present in database.
interface SignInServiceBase {
    fun signIn(user: UserDto): UserDto
}