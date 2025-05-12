package com.tserashkevich.appointmentservice.models;

import com.tserashkevich.appointmentservice.dtos.feign.DoctorResponse;
import com.tserashkevich.appointmentservice.dtos.feign.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "doctor_work_days")
public class DoctorWorkDay {
    @Id
    private String id;
    private DoctorResponse doctor;
    private List<ServiceResponse> services;
    private LocalDate date;
    private TimeInterval workTime;

}