package com.itis.stalkershop.models

data class UserDto(
    val email: String,
    val name: String,
    val passwordHash: String,
    val avatarId: Long? = null
) {
    fun toUser() = User(
        email,
        name,
        passwordHash,
        avatarId
    )
}




