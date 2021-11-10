package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.Token
import com.itis.stalkershop.models.UserDto

interface TokenService {
    fun add(token: Token)

    fun getUserEmail(token: String): String?

    fun exists(token: String): Boolean

    fun replace(token: Token)

    fun update(token: Token)

    fun delete(email: String)

    fun delete(user: UserDto) =
        delete(user.email)

    fun delete(token: Token)

    fun deleteByToken(token: String)

}