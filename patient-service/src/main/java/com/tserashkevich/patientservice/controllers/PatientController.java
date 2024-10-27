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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse createPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return patientService.create(patientRequest);
    }

    @PutMapping("/{id}")
    public PatientResponse updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequest patientRequest) {
        return patientService.update(id, patientRequest);
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
}
