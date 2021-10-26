package com.itis.stalkershop.models

data class UploadedFileDto(
    val originalFileName: String,
    val storageFileName: String,
    val size: Long,
    val type: String
)

fun UploadedFile.toDto() =
    UploadedFileDto(
        originalFileName,
        storageFileName,
        size,
        type
    )