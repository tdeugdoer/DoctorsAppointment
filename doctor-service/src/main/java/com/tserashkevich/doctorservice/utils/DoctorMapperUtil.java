package com.tserashkevich.doctorservice.utils;

import com.tserashkevich.doctorservice.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("DoctorMapperUtil")
@RequiredArgsConstructor
@Component
public class DoctorMapperUtil {
    private final ImageService imageService;

    @Named("getImageUrl")
    public String getImageUrl(String key) {
        return imageService.get(key);
    }
}
