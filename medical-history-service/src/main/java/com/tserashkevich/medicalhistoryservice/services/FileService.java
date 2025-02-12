package com.tserashkevich.medicalhistoryservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String upload(MultipartFile file);

    List<String> upload(List<MultipartFile> files);

    void delete(String key);

    void delete(List<String> keys);

    String get(String key);
}
