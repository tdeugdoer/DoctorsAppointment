package com.tserashkevich.appointmentservice.services.impl;

import com.tserashkevich.appointmentservice.dtos.feign.ServiceResponse;
import com.tserashkevich.appointmentservice.exceptions.AppointmentsGenerationException;
import com.tserashkevich.appointmentservice.exceptions.DoctorWorkDayNotFoundException;
import com.tserashkevich.appointmentservice.models.Appointment;
import com.tserashkevich.appointmentservice.models.DoctorWorkDay;
import com.tserashkevich.appointmentservice.models.enums.Status;
import com.tserashkevich.appointmentservice.repositories.AppointmentRepository;
import com.tserashkevich.appointmentservice.repositories.DoctorWorkDayRepository;
import com.tserashkevich.appointmentservice.services.AppointmentGenerateService;
import com.tserashkevich.appointmentservice.utils.LogList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentGenerateServiceImpl implements AppointmentGenerateService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorWorkDayRepository doctorWorkDayRepository;

    private static LocalTime getStartTime(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now())
                ? dateTime.toLocalTime()
                : LocalTime.now();
    }

    @Override
    public void generateAppointments(DoctorWorkDay doctorWorkDay) {
        LocalTime endTime = doctorWorkDay.getWorkTime().getEnd();
        checkEndTime(LocalDateTime.of(doctorWorkDay.getDate(), endTime));
        LocalTime startTime = getStartTime(LocalDateTime.of(doctorWorkDay.getDate(), doctorWorkDay.getWorkTime().getStart()));

        List<ServiceResponse> sortedServices = sortServicesByDurationDesc(doctorWorkDay.getServices());

        List<Appointment> appointments = createAppointments(startTime, endTime, doctorWorkDay, sortedServices);
        appointmentRepository.saveAll(appointments);
        log.info(LogList.GENERATE_APPOINTMENTS, doctorWorkDay.getId());
    }

    @Override
    public void regenerateAppointments(String doctorWorkDayId) {
        DoctorWorkDay doctorWorkDay = getOrThrow(doctorWorkDayId);

        LocalTime endTime = doctorWorkDay.getWorkTime().getEnd();
        checkEndTime(LocalDateTime.of(doctorWorkDay.getDate(), endTime));
        LocalTime startTime = getStartTime(LocalDateTime.of(doctorWorkDay.getDate(), doctorWorkDay.getWorkTime().getStart()));

        List<Appointment> sortAppointmentsByDate = sortAppointmentsByDate(appointmentRepository
                .findByDoctorWorkDayIdAndDateGreaterThanEqual(doctorWorkDay.getId(), LocalDateTime.of(doctorWorkDay.getDate(), startTime)));
        List<Appointment> freeSortAppointments = sortAppointmentsByDate.stream()
                .filter(appointment -> appointment.getStatus().equals(Status.FREE))
                .toList();
        List<Appointment> notFreeSortAppointments = sortAppointmentsByDate.stream()
                .filter(appointment -> !appointment.getStatus().equals(Status.FREE))
                .toList();
        List<ServiceResponse> sortedServices = sortServicesByDurationDesc(doctorWorkDay.getServices());

        List<Appointment> appointments = new ArrayList<>();
        LocalTime currentTime = startTime;
        for (Appointment notFreeAppointment : notFreeSortAppointments) {
            LocalTime appointmentBeginTime = notFreeAppointment.getDate().toLocalTime();
            appointments.addAll(createAppointments(currentTime, appointmentBeginTime, doctorWorkDay, sortedServices));
            currentTime = appointmentBeginTime.plusMinutes(notFreeAppointment.getService().getFirst().getDuration());
            if (currentTime.isAfter(endTime)) {
                break;
            }
        }
        appointments.addAll(createAppointments(currentTime, endTime, doctorWorkDay, sortedServices));

        appointmentRepository.deleteAll(freeSortAppointments);
        appointmentRepository.saveAll(appointments);
        log.info(LogList.REGENERATE_APPOINTMENTS, doctorWorkDay.getId());
    }

    @Override
    public void deleteAppointments(String doctorWorkDayId) {
        appointmentRepository.deleteAllByDoctorWorkDayId(doctorWorkDayId);
        log.info(LogList.DELETE_APPOINTMENTS, doctorWorkDayId);
    }

    private List<Appointment> createAppointments(LocalTime startTime, LocalTime endTime, DoctorWorkDay doctorWorkDay, List<ServiceResponse> sortedServices) {
        LocalTime currentTime = startTime;
        List<Appointment> appointments = new ArrayList<>();

        while (canFitNextAppointment(sortedServices.getFirst().getDuration(), currentTime, endTime)) {
            Appointment appointment = buildAppointment(doctorWorkDay, sortedServices, currentTime);
            appointments.add(appointment);
            currentTime = currentTime.plusMinutes(sortedServices.getFirst().getDuration());
        }

        List<ServiceResponse> servicesForLastAppointment = findFittingServices(currentTime, endTime, sortedServices);
        if (!servicesForLastAppointment.isEmpty()) {
            Appointment lastAppointment = buildAppointment(doctorWorkDay, servicesForLastAppointment, currentTime);
            appointments.add(lastAppointment);
        }

        return appointments;
    }

    private boolean canFitNextAppointment(int duration, LocalTime currentTime, LocalTime endTime) {
        return currentTime.plusMinutes(duration).isBefore(endTime);
    }

    private List<ServiceResponse> findFittingServices(LocalTime currentTime, LocalTime endTime, List<ServiceResponse> services) {
        return services.stream()
                .filter(service -> canFitNextAppointment(service.getDuration(), currentTime, endTime))
                .toList();
    }

    private Appointment buildAppointment(DoctorWorkDay doctorWorkDay, List<ServiceResponse> services, LocalTime time) {
        return Appointment.builder()
                .service(services)
                .doctor(doctorWorkDay.getDoctor())
                .status(Status.FREE)
                .date(LocalDateTime.of(doctorWorkDay.getDate(), time))
                .doctorWorkDayId(doctorWorkDay.getId())
                .build();
    }

    private List<ServiceResponse> sortServicesByDurationDesc(List<ServiceResponse> services) {
        return services.stream()
                .sorted(Comparator.comparingInt(ServiceResponse::getDuration).reversed())
                .toList();
    }

    private List<Appointment> sortAppointmentsByDate(List<Appointment> appointments) {
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getDate))
                .toList();
    }

    private void checkEndTime(LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new AppointmentsGenerationException();
        }
    }

    private DoctorWorkDay getOrThrow(String doctorWorkDayId) {
        Optional<DoctorWorkDay> optionalDoctorWorkDay = doctorWorkDayRepository.findById(doctorWorkDayId);
        return optionalDoctorWorkDay.orElseThrow(DoctorWorkDayNotFoundException::new);
    }

}
