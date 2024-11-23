package com.tserashkevich.appointmentservice.controllers;

import com.tserashkevich.appointmentservice.dtos.AppointmentResponse;
import com.tserashkevich.appointmentservice.dtos.CreateAppointmentRequest;
import com.tserashkevich.appointmentservice.dtos.FindAllParams;
import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.models.enums.Status;
import com.tserashkevich.appointmentservice.services.AppointmentService;
import com.tserashkevich.appointmentservice.utils.PatternList;
import com.tserashkevich.appointmentservice.utils.SortList;
import com.tserashkevich.appointmentservice.utils.ValidationList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse createAppointment(@Valid @RequestBody CreateAppointmentRequest createAppointmentRequest) {
        return appointmentService.create(createAppointmentRequest);
    }

    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable UUID appointmentId) {
        appointmentService.delete(appointmentId);
    }

    @GetMapping
    public PageResponse<AppointmentResponse> findAllAppointments(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                 @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                                 @RequestParam(defaultValue = "ID_ASC") SortList sort,
                                                                 @RequestParam(required = false) Status status,
                                                                 @RequestParam(required = false) LocalDateTime dateStart,
                                                                 @RequestParam(required = false) LocalDateTime dateEnd,
                                                                 @RequestParam(required = false) BigDecimal priceStart,
                                                                 @RequestParam(required = false) BigDecimal priceEnd) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .status(status)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .priceStart(priceStart)
                .priceEnd(priceEnd)
                .build();
        return appointmentService.findAll(findAllParams);
    }

    @GetMapping("/{appointmentId}")
    public AppointmentResponse findAppointmentById(@PathVariable UUID appointmentId) {
        return appointmentService.findById(appointmentId);
    }

    @GetMapping("/search/{searchLine}")
    public List<AppointmentResponse> searchAppointments(@PathVariable String searchLine) {
        return appointmentService.search(searchLine);
    }

    @PatchMapping("/book/{appointmentId}/{patientId}")
    public AppointmentResponse bookAppointment(@PathVariable UUID appointmentId,
                                               @NotBlank(message = ValidationList.PATIENT_ID_REQUIRED)
                                               @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
                                               @PathVariable String patientId) {
        return appointmentService.book(appointmentId, UUID.fromString(patientId));
    }

    @PatchMapping("/complete/{appointmentId}")
    public AppointmentResponse completeAppointment(@PathVariable UUID appointmentId) {
        return appointmentService.complete(appointmentId);
    }
}
