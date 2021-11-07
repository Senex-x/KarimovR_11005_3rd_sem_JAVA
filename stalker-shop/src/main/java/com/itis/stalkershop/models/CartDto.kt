package com.itis.stalkershop.models

data class CartDto(
    val userEmail: String,
    val itemList: List<ItemDto>,
    val totalCost: Int,
)