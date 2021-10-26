package com.itis.stalkershop.services.interfaces

interface PasswordEncoderBase {
    fun matches(
        password: String,
        hashPassword: String
    ): Boolean

    fun encode(
        password: String
    ): String
}