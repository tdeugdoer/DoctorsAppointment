package com.tserashkevich.appointmentservice.repositories;

import com.tserashkevich.appointmentservice.models.DoctorWorkDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorWorkDayRepository extends MongoRepository<DoctorWorkDay, String> {
    List<DoctorWorkDay> findByDate(LocalDate date);

}