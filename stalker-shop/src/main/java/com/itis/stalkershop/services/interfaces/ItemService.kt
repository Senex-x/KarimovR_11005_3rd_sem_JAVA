package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.ItemDto

interface ItemService {
    fun getItemDto(
        name: String
    ) : ItemDto
}