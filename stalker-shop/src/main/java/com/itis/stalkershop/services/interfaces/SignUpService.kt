package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserDto

interface SignUpService {
    fun signUp(user: UserDto)
}