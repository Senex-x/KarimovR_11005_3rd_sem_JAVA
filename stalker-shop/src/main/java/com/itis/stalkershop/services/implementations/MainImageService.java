package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.Image;
import com.itis.stalkershop.models.ImageDto;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.ImageRepository;
import com.itis.stalkershop.services.interfaces.ImageService;
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

public class MainImageService implements ImageService {
    private final String IMAGE_STORAGE_PATH;
    private final ImageRepository imageRepository;

    public MainImageService(
            String imageStoragePath,
            ImageRepository imageRepository
    ) {
        this.imageRepository = imageRepository;
        IMAGE_STORAGE_PATH = imageStoragePath;
    }

    @NotNull
    @Override
    public Image saveFileToStorage(
            @NotNull InputStream inputStream,
            @NotNull String originalFileName,
            @NotNull String contentType,
            long size
    ) {
        ImageDto fileInfo = new ImageDto(
                UUID.randomUUID().toString(),
                originalFileName,
                contentType,
                size
        );

        Image f;
        try {
            Files.copy(
                    inputStream,
                    Paths.get(IMAGE_STORAGE_PATH +
                            fileInfo.getStorageName() + "." +
                            fileInfo.getType().split("/")[1]
                    )
            );
            f = imageRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return f;
    }

    // TODO: inspect warning
    @Override
    public void writeFileFromStorage(
            long fileId,
            @NotNull OutputStream outputStream
    ) {
        Optional<Image> optionalFileInfo = imageRepository.findByPrimaryKey(fileId);
        Image fileInfo = optionalFileInfo.orElseThrow(
                () -> new NotFoundException("File not found")
        );

        File file = new File(IMAGE_STORAGE_PATH +
                fileInfo.getStorageName() + "." +
                fileInfo.getType().split("/")[1]
        );

        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: inspect warning
    @NotNull
    @Override
    public Image getFileInfo(long fileId) {
        return imageRepository.findByPrimaryKey(fileId).orElseThrow(
                () -> new NotFoundException("File not found")
        );
    }

    // TODO: Add actual deleting of image from file storage
    @Override
    public void deleteUserImageFromStorage(@NotNull UserDto user) {

    }
}
