package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.ItemDto

interface ItemService {
    fun add(newItem: ItemDto)

    fun get(name: String) : ItemDto?

    fun getAll() : List<ItemDto>
}