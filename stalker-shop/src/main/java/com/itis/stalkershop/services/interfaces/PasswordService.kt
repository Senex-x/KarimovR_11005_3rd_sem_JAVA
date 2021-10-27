package com.itis.stalkershop.services.interfaces

interface PasswordService {
    fun matches(
        password: String,
        passwordHash: String
    ): Boolean

    fun encode(
        password: String
    ): String
}