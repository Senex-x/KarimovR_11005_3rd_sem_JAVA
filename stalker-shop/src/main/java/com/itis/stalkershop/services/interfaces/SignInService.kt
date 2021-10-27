package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserAuth
import com.itis.stalkershop.models.UserDto

// Shows if user present in database.
interface SignInService {
    fun signIn(user: UserAuth): UserDto
}