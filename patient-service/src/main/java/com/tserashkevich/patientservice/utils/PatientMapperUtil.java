package com.tserashkevich.patientservice.utils;

import com.tserashkevich.patientservice.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("PatientMapperUtil")
@RequiredArgsConstructor
@Component
public class PatientMapperUtil {
    private final ImageService imageService;

    @Named("getImageUrl")
    public String getImageUrl(String key) {
        return imageService.get(key);
    }
}
