package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UserRegister

interface SignUpService {
    fun signUp(user: UserRegister)
}