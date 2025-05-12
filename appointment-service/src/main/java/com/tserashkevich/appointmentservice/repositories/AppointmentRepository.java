package com.tserashkevich.appointmentservice.repositories;

import com.tserashkevich.appointmentservice.models.Appointment;
import com.tserashkevich.appointmentservice.models.enums.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByStatusAndDoctor_Id(Status status, UUID doctorId, Sort sort);

    void deleteAllByDoctorWorkDayId(String doctorWorkDayId);

    List<Appointment> findByDoctorWorkDayIdAndDateGreaterThanEqual(String doctorWorkDayId, LocalDateTime date);

    @Query("{ $or: [ " +
            "{ 'doctor': { $regex: ?0, $options: 'i' } }," +
            "{ 'patient': { $regex: ?0, $options: 'i' } }," +
            "{ 'status': { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Appointment> search(@Param("search") String searchLine);

}