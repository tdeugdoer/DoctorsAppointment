package com.tserashkevich.medicalhistoryservice.controllers;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import com.tserashkevich.medicalhistoryservice.services.MedicalRecordService;
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
@RequestMapping(value = "/api/v1/records", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecordResponse createMedicalRecord(@Valid @RequestPart MedicalRecordRequest medicalRecordRequest,
                                                     @RequestPart List<MultipartFile> files) {
        return medicalRecordService.create(medicalRecordRequest, files);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MedicalRecordResponse updateMedicalRecord(@PathVariable UUID id,
                                                     @Valid @RequestPart MedicalRecordRequest medicalRecordRequest,
                                                     @RequestPart List<MultipartFile> files) {
        return medicalRecordService.update(id, medicalRecordRequest, files);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalRecord(@PathVariable UUID id) {
        medicalRecordService.delete(id);
    }

    @GetMapping
    public PageResponse<MedicalRecordResponse> findAllMedicalRecords(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                     @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                                     @RequestParam(defaultValue = "ID_ASC") SortList sort) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .build();
        return medicalRecordService.findAll(findAllParams);
    }

    @GetMapping("/{id}")
    public MedicalRecordResponse findMedicalRecordById(@PathVariable UUID id) {
        return medicalRecordService.findById(id);
    }

    @GetMapping("/search/{searchLine}")
    public List<MedicalRecordResponse> searchMedicalRecords(@PathVariable String searchLine) {
        return medicalRecordService.search(searchLine);
    }
}
