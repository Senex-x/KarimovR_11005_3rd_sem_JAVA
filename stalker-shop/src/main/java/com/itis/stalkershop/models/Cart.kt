package com.itis.stalkershop.models

data class Cart(
    val userEmail: String,
    val itemNamesJson: String
)