package com.itis.stalkershop.services.interfaces

interface PasswordEncoder {
    fun matches(
        password: String,
        hashPassword: String
    ): Boolean

    fun encode(
        password: String
    ): String
}