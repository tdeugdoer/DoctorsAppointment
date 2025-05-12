package com.tserashkevich.medicalhistoryservice.controllers;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import com.tserashkevich.medicalhistoryservice.dtos.UpdateMedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.services.MedicalRecordService;
import com.tserashkevich.medicalhistoryservice.utils.SortList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
                                                     @RequestPart(required = false) List<MultipartFile> files) {
        return medicalRecordService.create(medicalRecordRequest, files);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MedicalRecordResponse updateMedicalRecord(@PathVariable String id,
                                                     @Valid @RequestPart UpdateMedicalRecordRequest updateMedicalRecordRequest,
                                                     @RequestPart(required = false) List<MultipartFile> files) {
        return medicalRecordService.update(id, updateMedicalRecordRequest, files);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalRecord(@PathVariable String id) {
        medicalRecordService.delete(id);
    }

    @GetMapping
    public PageResponse<MedicalRecordResponse> findAllMedicalRecords(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                     @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                                     @RequestParam(defaultValue = "ID_ASC") SortList sort,
                                                                     @RequestParam(required = false) UUID patient,
                                                                     @RequestParam(required = false) UUID appointment,
                                                                     @RequestParam(required = false) UUID doctor,
                                                                     @RequestParam(required = false) String diagnosis,
                                                                     @RequestParam(required = false) LocalDate dateOfVisitStart,
                                                                     @RequestParam(required = false) LocalDate dateOfVisitEnd) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .patient(patient)
                .appointment(appointment)
                .doctor(doctor)
                .diagnosis(diagnosis)
                .dateOfVisitStart(dateOfVisitStart)
                .dateOfVisitEnd(dateOfVisitEnd)
                .build();
        return medicalRecordService.findAll(findAllParams);
    }

    @GetMapping("/{id}")
    public MedicalRecordResponse findMedicalRecordById(@PathVariable String id) {
        return medicalRecordService.findById(id);
    }

    @GetMapping("/search/{searchLine}")
    public List<MedicalRecordResponse> searchMedicalRecords(@PathVariable String searchLine) {
        return medicalRecordService.search(searchLine);
    }

    @DeleteMapping("/{medicalRecordId}/delete-file/{fileKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable String medicalRecordId,
                           @PathVariable String fileKey) {
        medicalRecordService.deleteFile(medicalRecordId, fileKey);
    }

}
