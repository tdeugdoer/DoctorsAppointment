package com.tserashkevich.appointmentservice.feign;

import com.tserashkevich.appointmentservice.dtos.feign.DoctorResponse;
import com.tserashkevich.appointmentservice.dtos.feign.PatientResponse;
import com.tserashkevich.appointmentservice.dtos.feign.ServiceResponse;
import com.tserashkevich.appointmentservice.exceptions.DoctorNotExistException;
import com.tserashkevich.appointmentservice.exceptions.PatientNotExistException;
import com.tserashkevich.appointmentservice.exceptions.ServiceNotExistException;
import com.tserashkevich.appointmentservice.exceptions.feign.OtherServiceNotFoundException;
import com.tserashkevich.appointmentservice.utils.LogList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExternalServiceClient {
    private final ServiceFeignClient serviceFeignClient;
    private final DoctorFeignClient doctorFeignClient;
    private final PatientFeignClient patientFeignClient;

    public List<ServiceResponse> getServices(List<String> serviceIds) {
        try {
            String stringServiceIds = String.join(",", serviceIds);
            List<ServiceResponse> serviceResponses = serviceFeignClient.findServices(stringServiceIds);

            log.info(LogList.RECEIVED_SERVICES, stringServiceIds);
            return serviceResponses;
        } catch (OtherServiceNotFoundException e) {
            throw new ServiceNotExistException();
        }
    }

    public DoctorResponse getDoctor(UUID doctorId) {
        try {
            DoctorResponse doctorResponse = doctorFeignClient.findDoctor(doctorId);

            log.info(LogList.RECEIVED_DOCTOR, doctorId);
            return doctorResponse;
        } catch (OtherServiceNotFoundException e) {
            throw new DoctorNotExistException();
        }
    }

    public PatientResponse getPatient(UUID patientId) {
        try {
            PatientResponse patientResponse = patientFeignClient.findPatient(patientId);
            log.info(LogList.RECEIVED_PATIENT, patientId);

            return patientResponse;
        } catch (OtherServiceNotFoundException e) {
            throw new PatientNotExistException();
        }
    }

}
