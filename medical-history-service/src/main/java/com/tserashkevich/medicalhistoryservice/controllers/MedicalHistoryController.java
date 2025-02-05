package com.tserashkevich.medicalhistoryservice.controllers;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalHistoryRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalHistoryResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import com.tserashkevich.medicalhistoryservice.services.MedicalHistoryService;
import com.tserashkevich.medicalhistoryservice.utils.SortList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/api/v1/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalHistoryResponse createMedicalHistory(@Valid @RequestPart MedicalHistoryRequest medicalHistoryRequest,
                                                       @RequestPart MultipartFile file) {
        return medicalHistoryService.create(medicalHistoryRequest, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MedicalHistoryResponse updateMedicalHistory(@PathVariable UUID id,
                                                       @Valid @RequestPart MedicalHistoryRequest medicalHistoryRequest,
                                                       @RequestPart MultipartFile file) {
        return medicalHistoryService.update(id, medicalHistoryRequest, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalHistory(@PathVariable UUID id) {
        medicalHistoryService.delete(id);
    }

    @GetMapping
    public PageResponse<MedicalHistoryResponse> findAllMedicalHistories(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                        @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                                        @RequestParam(defaultValue = "ID_ASC") SortList sort) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .build();
        return medicalHistoryService.findAll(findAllParams);
    }

    @GetMapping("/{id}")
    public MedicalHistoryResponse findMedicalHistoryById(@PathVariable UUID id) {
        return medicalHistoryService.findById(id);
    }

    @GetMapping("/search/{searchLine}")
    public List<MedicalHistoryResponse> searchMedicalHistories(@PathVariable String searchLine) {
        return medicalHistoryService.search(searchLine);
    }
}
