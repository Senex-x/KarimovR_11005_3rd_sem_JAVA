package com.itis.stalkershop.models

data class User(
    val email: String,
    val name: String,
    val passwordHash: String,
    val avatarId: Long? = null
) {
    fun toDto() =
        UserDto(
            email,
            name,
            passwordHash,
            avatarId
        )
}