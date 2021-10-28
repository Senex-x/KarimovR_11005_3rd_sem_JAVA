package com.itis.stalkershop.models

class ItemDto(
    val name: String,
    val cost: Int,
    val description: String,
    val imageName: String
) {
    fun toItem() = Item(
        name,
        cost,
        description,
        imageName
    )
}
