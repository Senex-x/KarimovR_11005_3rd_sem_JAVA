package com.itis.stalkershop.models

data class Item(
    val name: String,
    val cost: Int,
    val description: String,
    val imageName: String
) {
    fun toItemDto() = ItemDto(
        name,
        cost,
        description,
        imageName
    )
}