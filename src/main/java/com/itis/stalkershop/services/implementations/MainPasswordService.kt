package com.itis.stalkershop.services.implementations

import com.itis.stalkershop.services.interfaces.PasswordService
import java.math.BigInteger
import java.security.MessageDigest

class MainPasswordService : PasswordService {
    override fun matches(
        password: String,
        passwordHash: String
    ) = password.encode() == passwordHash

    override fun encode(
        password: String
    ) = password.encode()
}

private fun String.encode() =
    BigInteger(
        1,
        MessageDigest
            .getInstance("MD5")
            .digest(toByteArray())
    )
        .toString(16)
        .padStart(32, '0')


