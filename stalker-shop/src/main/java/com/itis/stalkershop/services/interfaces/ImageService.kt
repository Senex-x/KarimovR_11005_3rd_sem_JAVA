package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.Image
import java.io.InputStream
import java.io.OutputStream

interface ImageService {
    fun saveFileToStorage(
        file: InputStream,
        originalFileName: String,
        contentType: String,
        size: Long
    ): Image

    fun writeFileFromStorage(
        fileId: Long,
        outputStream: OutputStream
    )

    fun getFileInfo(
        fileId: Long
    ): Image
}