package com.itis.stalkershop.models

data class Image(
    val id: Long,
    val storageName: String,
    val originalName: String,
    val type: String,
    val size: Long
) {
    fun toImageDto() =
        ImageDto(
            storageName,
            originalName,
            type,
            size
        )
}