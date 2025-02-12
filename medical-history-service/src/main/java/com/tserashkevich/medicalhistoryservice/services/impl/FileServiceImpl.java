package com.tserashkevich.medicalhistoryservice.services.impl;

import com.tserashkevich.medicalhistoryservice.configs.minio.MinioProperties;
import com.tserashkevich.medicalhistoryservice.exceptions.BadFileException;
import com.tserashkevich.medicalhistoryservice.exceptions.FileProcessingException;
import com.tserashkevich.medicalhistoryservice.services.FileService;
import com.tserashkevich.medicalhistoryservice.utils.LogList;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
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
            log.error(e.getMessage());
            throw new FileProcessingException();
        }

        log.info(LogList.UPLOAD_FILE, key);
        return key;
    }

    @Override
    public List<String> upload(List<MultipartFile> files) {
        return files.stream().map(this::upload).toList();
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
            log.error(e.getMessage());
            throw new FileProcessingException();
        }
        log.info(LogList.DELETE_FILE, key);
    }

    @Override
    public void delete(List<String> keys) {
        keys.forEach(this::delete);
    }

    @Override
    public String get(String key) {
        try {
            log.info(LogList.GET_FILE, key);
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .method(Method.GET)
                            .object(key)
                            .expiry(10, TimeUnit.MINUTES)
                            .build()
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FileProcessingException();
        }
    }

    private void fileCheck(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new BadFileException();
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
