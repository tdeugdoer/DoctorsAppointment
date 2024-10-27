package com.tserashkevich.doctorservice.controllers;

import com.tserashkevich.doctorservice.dtos.DoctorRequest;
import com.tserashkevich.doctorservice.dtos.DoctorResponse;
import com.tserashkevich.doctorservice.dtos.FindAllParams;
import com.tserashkevich.doctorservice.dtos.PageResponse;
import com.tserashkevich.doctorservice.models.enums.Gender;
import com.tserashkevich.doctorservice.models.enums.Specialization;
import com.tserashkevich.doctorservice.services.DoctorService;
import com.tserashkevich.doctorservice.utils.SortList;
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
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse createDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        return doctorService.create(doctorRequest);
    }

    @PutMapping("/{id}")
    public DoctorResponse updateDoctor(@PathVariable UUID id, @Valid @RequestBody DoctorRequest doctorRequest) {
        return doctorService.update(id, doctorRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable UUID id) {
        doctorService.delete(id);
    }

    @GetMapping
    public PageResponse<DoctorResponse> findAllDoctors(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                       @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                       @RequestParam(defaultValue = "ID_ASC") SortList sort,
                                                       @RequestParam(required = false) Specialization specialization,
                                                       @RequestParam(required = false) Gender gender,
                                                       @RequestParam(required = false) Integer experienceStart,
                                                       @RequestParam(required = false) Integer experienceEnd,
                                                       @RequestParam(required = false) LocalDate birthDateStart,
                                                       @RequestParam(required = false) LocalDate birthDateEnd) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .specialization(specialization)
                .gender(gender)
                .experienceStart(experienceStart)
                .experienceEnd(experienceEnd)
                .birthDateStart(birthDateStart)
                .birthDateEnd(birthDateEnd)
                .build();
        return doctorService.findAll(findAllParams);
    }

    @GetMapping("/{id}")
    public DoctorResponse findDoctorById(@PathVariable UUID id) {
        return doctorService.findById(id);
    }

    @GetMapping("/search/{searchLine}")
    public List<DoctorResponse> searchDoctors(@PathVariable String searchLine) {
        return doctorService.search(searchLine);
    }
}
