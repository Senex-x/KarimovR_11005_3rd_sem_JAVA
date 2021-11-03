package com.itis.stalkershop.models

import com.itis.stalkershop.utils.JsonString

data class Cart(
    val userEmail: String,
    val itemNamesJson: JsonString
)