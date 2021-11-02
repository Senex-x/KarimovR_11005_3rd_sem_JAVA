package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.Cart
import com.itis.stalkershop.models.CartDto
import com.itis.stalkershop.models.UserDto

interface CartService {
    fun get(userEmail: String): CartDto?

    fun get(user: UserDto): CartDto? =
        get(user.email)

    fun addItem(userEmail: String, itemName: String)

    fun addItem(user: UserDto, itemName: String) =
        addItem(user.email, itemName)
}