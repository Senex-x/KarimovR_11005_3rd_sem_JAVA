package com.itis.servletsapp.Database;

import java.io.InputStream;
import java.io.OutputStream;

//TODO: дз на 16.10
public interface FilesService {
    FileInfo saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size);
    void readFileFromStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
}
