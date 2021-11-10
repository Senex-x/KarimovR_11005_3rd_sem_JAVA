package com.itis.stalkershop.utils.exceptions

class NotFoundException(message: String?) :
    ValidationException(
        ErrorEntity.NOT_FOUND,
        message
    )


