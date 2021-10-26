package com.itis.stalkershop.utils.exceptions

open class ValidationException
@JvmOverloads
constructor(
    val entity: ErrorEntity,
    optionalMessage: String? = null
) : RuntimeException(
    optionalMessage ?: entity.message
)


