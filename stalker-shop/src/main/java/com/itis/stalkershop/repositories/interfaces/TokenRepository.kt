package com.itis.stalkershop.repositories.interfaces

import com.itis.stalkershop.models.Token

interface TokenRepository : ReworkedRepository<String, Token> {
    fun getByToken(token: String): Token?

    // This is more safe, than delete it by user email,
    // because user can have several accounts,
    // and that's how we invalidate previous tokens
    fun deleteByToken(token: String)
}