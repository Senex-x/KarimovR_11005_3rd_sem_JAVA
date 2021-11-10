package com.itis.stalkershop.models

data class ImageDto(
    val storageName: String,
    val originalName: String,
    val type: String,
    val size: Long
) {
    fun toImage(primaryKey: Long) = Image(
        primaryKey,
        storageName,
        originalName,
        type,
        size
    )
}


