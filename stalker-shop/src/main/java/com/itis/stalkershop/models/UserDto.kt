package com.itis.stalkershop.models

data class UserDto(
    val email: String,
    val name: String,
    val passwordHash: String,
    val avatarId: Long? = null
)

fun User.toDto() =
    UserDto(
        email,
        name,
        passwordHash,
        avatarId
    )

