package com.tserashkevich.patientservice.controllers;

import com.tserashkevich.patientservice.dtos.FindAllParams;
import com.tserashkevich.patientservice.dtos.PageResponse;
import com.tserashkevich.patientservice.dtos.PatientRequest;
import com.tserashkevich.patientservice.dtos.PatientResponse;
import com.tserashkevich.patientservice.models.enums.Gender;
import com.tserashkevich.patientservice.services.PatientService;
import com.tserashkevich.patientservice.utils.SortList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse createPatient(@Valid @RequestPart PatientRequest patientRequest,
                                         @RequestPart MultipartFile file) {
        return patientService.create(patientRequest, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PatientResponse updatePatient(@PathVariable UUID id,
                                         @Valid @RequestPart PatientRequest patientRequest,
                                         @RequestPart MultipartFile file) {
        return patientService.update(id, patientRequest, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable UUID id) {
        patientService.delete(id);
    }

    @GetMapping
    public PageResponse<PatientResponse> findAllPatients(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                         @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                         @RequestParam(defaultValue = "ID_ASC") SortList sort,
                                                         @RequestParam(required = false) Gender gender,
                                                         @RequestParam(required = false) LocalDate birthDateStart,
                                                         @RequestParam(required = false) LocalDate birthDateEnd) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .gender(gender)
                .birthDateStart(birthDateStart)
                .birthDateEnd(birthDateEnd)
                .build();
        return patientService.findAll(findAllParams);
    }

    @GetMapping("/{id}")
    public PatientResponse findPatientById(@PathVariable UUID id) {
        return patientService.findById(id);
    }

    @GetMapping("/search/{searchLine}")
    public List<PatientResponse> searchPatients(@PathVariable String searchLine) {
        return patientService.search(searchLine);
    }

    @GetMapping("/exist/{patientId}")
    public Boolean exitsPatient(@PathVariable UUID patientId) {
        return patientService.exist(patientId);
    }
}
