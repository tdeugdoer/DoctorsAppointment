package com.tserashkevich.appointmentservice.services;

import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayFindAllParams;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayRequest;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayResponse;

public interface DoctorWorkDayService {
    DoctorWorkDayResponse create(DoctorWorkDayRequest doctorWorkDayRequest);

    void delete(String doctorWorkDayId);

    PageResponse<DoctorWorkDayResponse> findAll(DoctorWorkDayFindAllParams doctorWorkDayFindAllParams);

    DoctorWorkDayResponse findById(String doctorWorkDayId);

}
