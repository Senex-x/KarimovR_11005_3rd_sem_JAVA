package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.UploadedFile;
import com.itis.stalkershop.models.UploadedFileDto;
import com.itis.stalkershop.repositories.interfaces.FilesRepository;
import com.itis.stalkershop.services.interfaces.FilesService;
import com.itis.stalkershop.utils.exceptions.NotFoundException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class FilesServiceMain implements FilesService {

    private static String path = "C:\\Users\\Senex\\Pictures\\Saved Pictures\\";

    private final FilesRepository filesRepository;

    public FilesServiceMain(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @NotNull
    @Override
    public UploadedFile saveFileToStorage(
            @NotNull InputStream inputStream,
            @NotNull String originalFileName,
            @NotNull String contentType,
            long size
    ) {
        UploadedFileDto fileInfo = new UploadedFileDto(
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );

        UploadedFile f;
        try {
            Files.copy(inputStream,
                    Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            f = filesRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return f;
    }

    @Override
    public void writeFileFromStorage(
            long fileId,
            @NotNull OutputStream outputStream
    ) {
        Optional<UploadedFile> optionalFileInfo = filesRepository.findByPrimaryKey(fileId);
        UploadedFile fileInfo = optionalFileInfo.orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @NotNull
    @Override
    public UploadedFile getFileInfo(long fileId) {
        return filesRepository.findByPrimaryKey(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }


}
