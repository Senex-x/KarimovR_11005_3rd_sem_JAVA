package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.CartDto
import com.itis.stalkershop.models.UserDto

interface CartService {
    fun get(userEmail: String): CartDto

    fun get(user: UserDto): CartDto =
        get(user.email)

    fun addItem(userEmail: String, newItemName: String)

    fun addItem(user: UserDto, newItemName: String) =
        addItem(user.email, newItemName)

    fun delete(userEmail: String)

    fun delete(user: UserDto) =
        delete(user.email)

    fun deleteItem(userEmail: String, itemName: String)

    fun deleteItem(user: UserDto, itemName: String) =
        deleteItem(user.email, itemName)
}