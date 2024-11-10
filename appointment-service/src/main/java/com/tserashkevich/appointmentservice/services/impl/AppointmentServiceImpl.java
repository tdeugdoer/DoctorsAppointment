package com.tserashkevich.appointmentservice.services.impl;

import com.querydsl.core.types.Predicate;
import com.tserashkevich.appointmentservice.dtos.AppointmentResponse;
import com.tserashkevich.appointmentservice.dtos.CreateAppointmentRequest;
import com.tserashkevich.appointmentservice.dtos.FindAllParams;
import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.exceptions.AppointmentNotFoundException;
import com.tserashkevich.appointmentservice.mappers.AppointmentMapper;
import com.tserashkevich.appointmentservice.models.Appointment;
import com.tserashkevich.appointmentservice.models.QAppointment;
import com.tserashkevich.appointmentservice.models.enums.Status;
import com.tserashkevich.appointmentservice.repositories.AppointmentRepository;
import com.tserashkevich.appointmentservice.services.AppointmentService;
import com.tserashkevich.appointmentservice.utils.LogList;
import com.tserashkevich.appointmentservice.utils.QPredicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponse create(CreateAppointmentRequest createAppointmentRequest) {
        Appointment appointment = appointmentMapper.toModel(createAppointmentRequest);

        appointment.setStatus(Status.FREE);
        appointmentRepository.save(appointment);

        log.info(LogList.CREATE_APPOINTMENT, appointment.getId());
        return appointmentMapper.toResponse(appointment);

    }

    @Override
    public void delete(UUID appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);

        appointmentRepository.delete(appointment);

        log.info(LogList.DELETE_APPOINTMENT, appointmentId);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<AppointmentResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Predicate predicate = QPredicates.builder()
                .add(findAllParams.getStatus(), QAppointment.appointment.status::eq)
                .add(findAllParams.getDateStart(), QAppointment.appointment.date::before)
                .add(findAllParams.getDateEnd(), QAppointment.appointment.date::after)
                .add(findAllParams.getPriceStart(), QAppointment.appointment.price::goe)
                .add(findAllParams.getPriceEnd(), QAppointment.appointment.price::lt)
                .build();

        Page<Appointment> appointmentPage = appointmentRepository.findAll(predicate, pageable);
        List<AppointmentResponse> appointmentResponses = appointmentMapper.toResponses(appointmentPage.getContent());

        log.info(LogList.FIND_ALL_APPOINTMENTS);
        return PageResponse.<AppointmentResponse>builder()
                .objectList(appointmentResponses)
                .totalElements(appointmentPage.getTotalElements())
                .totalPages(appointmentPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public AppointmentResponse findById(UUID appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);
        log.info(LogList.FIND_APPOINTMENT, appointmentId);
        return appointmentMapper.toResponse(appointment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AppointmentResponse> search(String searchLine) {
        log.info(LogList.SEARCH_APPOINTMENT, searchLine);
        return appointmentMapper.toResponses(appointmentRepository.search(searchLine));
    }

    @Override
    public AppointmentResponse book(UUID appointmentId, UUID patientId) {
        Appointment appointment = getOrThrow(appointmentId);

        appointment.setPatient(patientId);
        appointment.setStatus(Status.BOOKED);
        appointmentRepository.save(appointment);

        log.info(LogList.BOOK_APPOINTMENT, appointment, patientId);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse complete(UUID appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);

        appointment.setStatus(Status.COMPLETED);
        appointmentRepository.save(appointment);

        log.info(LogList.COMPLETE_APPOINTMENT, appointmentId);
        return appointmentMapper.toResponse(appointment);
    }

    public Appointment getOrThrow(UUID appointmentId) {
        Optional<Appointment> optionalPassenger = appointmentRepository.findById(appointmentId);
        return optionalPassenger.orElseThrow(AppointmentNotFoundException::new);
    }
}
