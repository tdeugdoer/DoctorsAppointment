package com.tserashkevich.doctorservice.services.impl;

import com.tserashkevich.doctorservice.configs.minio.MinioProperties;
import com.tserashkevich.doctorservice.exceptions.BadImageException;
import com.tserashkevich.doctorservice.exceptions.ImageProcessingException;
import com.tserashkevich.doctorservice.services.ImageService;
import com.tserashkevich.doctorservice.utils.LogList;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) {
        fileCheck(file);
        String key = generateKey(file);

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .stream(file.getInputStream(), file.getInputStream().available(), -1)
                            .bucket(minioProperties.getBucket())
                            .object(key)
                            .build()
            );
        } catch (Exception e) {
            throw new ImageProcessingException();
        }

        log.info(LogList.UPLOAD_IMAGE, key);
        return key;
    }

    @Override
    public void update(String key, MultipartFile file) {
        fileCheck(file);
        if (key.isEmpty()) {
            key = generateKey(file);
        }

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .stream(file.getInputStream(), file.getInputStream().available(), -1)
                            .bucket(minioProperties.getBucket())
                            .object(key)
                            .build()
            );
        } catch (Exception e) {
            throw new ImageProcessingException();
        }
        log.info(LogList.UPDATE_IMAGE, key);
    }

    @Override
    public void delete(String key) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(key)
                            .build()
            );
        } catch (Exception e) {
            throw new ImageProcessingException();
        }
        log.info(LogList.DELETE_IMAGE, key);
    }

    @Override
    public String get(String key) {
        return key != null
                ? String.format("http://localhost:%d/%s/%s", minioProperties.getPort(),
                minioProperties.getBucket(), key)
                : "";
    }

    private void fileCheck(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new BadImageException();
        }
    }

    private String generateKey(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename()
                        .lastIndexOf(".") + 1);
    }
}
