package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserDto

interface SignUpServiceBase {
    fun signUp(user: UserDto)
}