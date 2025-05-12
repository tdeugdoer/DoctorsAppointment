package com.tserashkevich.appointmentservice.controllers;

import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentFindAllParams;
import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentResponse;
import com.tserashkevich.appointmentservice.models.enums.Status;
import com.tserashkevich.appointmentservice.services.AppointmentService;
import com.tserashkevich.appointmentservice.utils.AppointmentSortList;
import com.tserashkevich.appointmentservice.utils.PatternList;
import com.tserashkevich.appointmentservice.utils.ValidationList;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public PageResponse<AppointmentResponse> findAllAppointments(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                 @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                                 @RequestParam(defaultValue = "ID_ASC") AppointmentSortList sort,
                                                                 @RequestParam(required = false) Status status,
                                                                 @RequestParam(required = false) LocalDateTime dateStart,
                                                                 @RequestParam(required = false) LocalDateTime dateEnd,
                                                                 @RequestParam(required = false) BigDecimal priceStart,
                                                                 @RequestParam(required = false) BigDecimal priceEnd) {
        AppointmentFindAllParams appointmentFindAllParams = AppointmentFindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .status(status)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .priceStart(priceStart)
                .priceEnd(priceEnd)
                .build();
        return appointmentService.findAll(appointmentFindAllParams);
    }

    @GetMapping("/free/{doctorId}")
    public List<AppointmentResponse> findDoctorFreeAppointments(@PathVariable UUID doctorId) {
        return appointmentService.findFreeWithDoctorId(doctorId);
    }

    @GetMapping("/{appointmentId}")
    public AppointmentResponse findAppointmentById(@PathVariable String appointmentId) {
        return appointmentService.findById(appointmentId);
    }

    @GetMapping("/search/{searchLine}")
    public List<AppointmentResponse> searchAppointments(@PathVariable String searchLine) {
        return appointmentService.search(searchLine);
    }

    @PatchMapping("/free/{appointmentId}")
    public AppointmentResponse freeAppointment(@PathVariable String appointmentId) {
        return appointmentService.free(appointmentId);
    }

    @PatchMapping("/book")
    public AppointmentResponse bookAppointment(@RequestParam String appointmentId,
                                               @NotBlank(message = ValidationList.PATIENT_ID_REQUIRED)
                                               @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
                                               @RequestParam String patientId,
                                               @NotBlank(message = ValidationList.PATIENT_ID_REQUIRED)
                                               @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
                                               @RequestParam String serviceId) {
        return appointmentService.book(appointmentId, UUID.fromString(patientId), UUID.fromString(serviceId));
    }

    @PatchMapping("/check-in/{appointmentId}")
    public AppointmentResponse checkInAppointment(@PathVariable String appointmentId) {
        return appointmentService.checkIn(appointmentId);
    }

    @PatchMapping("/in-progress/{appointmentId}")
    public AppointmentResponse inProgressAppointment(@PathVariable String appointmentId) {
        return appointmentService.inProgress(appointmentId);
    }

    @PatchMapping("/complete/{appointmentId}")
    public AppointmentResponse completeAppointment(@PathVariable String appointmentId) {
        return appointmentService.complete(appointmentId);
    }

    @PatchMapping("/no-show/{appointmentId}")
    public AppointmentResponse noShowAppointment(@PathVariable String appointmentId) {
        return appointmentService.noShow(appointmentId);
    }

}
