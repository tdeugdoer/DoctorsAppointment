package com.tserashkevich.patientservice.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String upload(MultipartFile file);

    void update(String key, MultipartFile file);

    void delete(String key);

    String get(String key);
}
