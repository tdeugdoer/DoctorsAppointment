package com.tserashkevich.appointmentservice.models;

import com.tserashkevich.appointmentservice.dtos.feign.DoctorResponse;
import com.tserashkevich.appointmentservice.dtos.feign.ServiceResponse;
import com.tserashkevich.appointmentservice.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "appointments")
public class Appointment {
    @Id
    private String id;
    private List<ServiceResponse> service;
    private DoctorResponse doctor;
    private UUID patient;
    private Status status;
    private LocalDateTime date;
    private BigDecimal price;
    private String doctorWorkDayId;

}
