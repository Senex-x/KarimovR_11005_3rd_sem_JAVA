package com.itis.stalkershop.models

data class UploadedFileDto(
    val originalFileName: String,
    val storageFileName: String,
    val size: Long,
    val type: String
) {
    fun toUploadedFile(primaryKey: Long) = UploadedFile(
        primaryKey,
        originalFileName,
        storageFileName,
        size,
        type
    )
}


