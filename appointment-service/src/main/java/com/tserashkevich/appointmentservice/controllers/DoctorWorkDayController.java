package com.tserashkevich.appointmentservice.controllers;

import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayFindAllParams;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayRequest;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayResponse;
import com.tserashkevich.appointmentservice.services.DoctorWorkDayService;
import com.tserashkevich.appointmentservice.utils.DoctorWorkDaySortList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/api/v1/work-days", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorWorkDayController {
    private final DoctorWorkDayService doctorWorkDayService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorWorkDayResponse createDoctorWorkDay(@Valid @RequestBody DoctorWorkDayRequest doctorWorkDayRequest) {
        return doctorWorkDayService.create(doctorWorkDayRequest);
    }

    @DeleteMapping("/{doctorWorkDayId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctorWorkDay(@PathVariable String doctorWorkDayId) {
        doctorWorkDayService.delete(doctorWorkDayId);
    }

    @GetMapping
    public PageResponse<DoctorWorkDayResponse> findAllDoctorWorkDays(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                     @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                                     @RequestParam(defaultValue = "ID_ASC") DoctorWorkDaySortList sort,
                                                                     @RequestParam(required = false) UUID doctor,
                                                                     @RequestParam(required = false) List<UUID> services,
                                                                     @RequestParam(required = false) LocalDate dateStart,
                                                                     @RequestParam(required = false) LocalDate dateEnd) {
        DoctorWorkDayFindAllParams doctorWorkDayFindAllParams = DoctorWorkDayFindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .doctor(doctor)
                .services(services)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .build();
        return doctorWorkDayService.findAll(doctorWorkDayFindAllParams);
    }

    @GetMapping("/{doctorWorkDayId}")
    public DoctorWorkDayResponse findDoctorWorkDayById(@PathVariable String doctorWorkDayId) {
        return doctorWorkDayService.findById(doctorWorkDayId);
    }

}
