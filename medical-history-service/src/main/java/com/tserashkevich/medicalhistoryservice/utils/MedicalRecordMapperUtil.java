package com.tserashkevich.medicalhistoryservice.utils;

import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.services.FileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("DoctorMapperUtil")
@RequiredArgsConstructor
@Component
public class MedicalRecordMapperUtil {
    private final FileService fileService;

    @Named("getFileInformation")
    public MedicalRecordResponse.FileInformation getImageUrl(String fileKey) {
        return MedicalRecordResponse.FileInformation.builder()
                .fileKey(fileKey)
                .link(fileService.get(fileKey))
                .build();
    }

}
