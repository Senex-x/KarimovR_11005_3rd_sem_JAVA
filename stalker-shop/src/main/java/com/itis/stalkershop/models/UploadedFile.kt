package com.itis.stalkershop.models

data class UploadedFile(
    val id: Long,
    val originalFileName: String,
    val storageFileName: String,
    val size: Long,
    val type: String
) {
    fun toDto() =
        UploadedFileDto(
            originalFileName,
            storageFileName,
            size,
            type
        )
}