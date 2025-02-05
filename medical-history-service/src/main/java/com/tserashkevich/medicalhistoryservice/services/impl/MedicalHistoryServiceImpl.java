package com.tserashkevich.medicalhistoryservice.services.impl;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalHistoryRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalHistoryResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import com.tserashkevich.medicalhistoryservice.services.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    @Override
    public MedicalHistoryResponse create(MedicalHistoryRequest medicalHistoryRequest, MultipartFile file) {
        return null;
    }

    @Override
    public MedicalHistoryResponse update(UUID id, MedicalHistoryRequest medicalHistoryRequest, MultipartFile file) {
        return null;
    }

    @Override
    public void delete(UUID medicalHistoryId) {

    }

    @Override
    public PageResponse<MedicalHistoryResponse> findAll(FindAllParams findAllParams) {
        return null;
    }

    @Override
    public MedicalHistoryResponse findById(UUID medicalHistoryId) {
        return null;
    }

    @Override
    public List<MedicalHistoryResponse> search(String searchLine) {
        return null;
    }
}
