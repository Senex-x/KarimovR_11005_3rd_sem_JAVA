package com.itis.stalkershop.services.interfaces

import com.itis.stalkershop.models.UploadedFile
import java.io.InputStream
import java.io.OutputStream

interface FilesServiceBase {
    fun saveFileToStorage(
        file: InputStream,
        originalFileName: String,
        contentType: String,
        size: Long
    ): UploadedFile

    fun writeFileFromStorage(
        fileId: Long,
        outputStream: OutputStream
    )

    fun getFileInfo(
        fileId: Long
    ): UploadedFile
}