package com.itis.stalkershop.services.implementations

import com.itis.stalkershop.models.Token
import com.itis.stalkershop.repositories.interfaces.TokenRepository
import com.itis.stalkershop.services.interfaces.TokenService

class MainTokenService(
    private val tokenRepository: TokenRepository,
) : TokenService {
    override fun add(token: Token) =
        tokenRepository.add(token)

    override fun getUserEmail(token: String): String? =
        tokenRepository.getByToken(token)?.userEmail

    override fun exists(token: String) =
        tokenRepository[token] != null

    override fun replace(token: Token) {
        delete(token)
        add(token)
    }

    override fun update(token: Token) =
        tokenRepository.update(token)

    override fun delete(email: String) =
        tokenRepository.delete(email)

    override fun delete(token: Token) {
        tokenRepository.delete(token.userEmail)
        tokenRepository.deleteByToken(token.token)
    }

    override fun deleteByToken(token: String) {
        tokenRepository.deleteByToken(token)
    }
}
