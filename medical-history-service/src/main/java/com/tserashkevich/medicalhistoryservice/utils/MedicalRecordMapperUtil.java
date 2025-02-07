package com.tserashkevich.medicalhistoryservice.utils;

import com.tserashkevich.medicalhistoryservice.services.FileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("DoctorMapperUtil")
@RequiredArgsConstructor
@Component
public class MedicalRecordMapperUtil {
    private final FileService fileService;

    @Named("getFileUrl")
    public String getImageUrl(String key) {
        return fileService.get(key);
    }
}
