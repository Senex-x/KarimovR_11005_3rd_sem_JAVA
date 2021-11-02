package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.Cart
import com.itis.stalkershop.models.CartDto
import com.itis.stalkershop.models.UserDto

interface CartService {
    fun get(user: UserDto): CartDto?

    fun get(userName: String): CartDto?

    fun add(user: UserDto, cart: Cart)

    fun add(userName: String, cart: Cart)
}