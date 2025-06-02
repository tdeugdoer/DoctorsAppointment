package com.tserashkevich.appointmentservice.services.impl;

import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentFindAllParams;
import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentResponse;
import com.tserashkevich.appointmentservice.exceptions.AppointmentAlreadyCompletedException;
import com.tserashkevich.appointmentservice.exceptions.AppointmentAlreadyNoShowException;
import com.tserashkevich.appointmentservice.exceptions.AppointmentNotFoundException;
import com.tserashkevich.appointmentservice.feign.ExternalServiceClient;
import com.tserashkevich.appointmentservice.mappers.AppointmentMapper;
import com.tserashkevich.appointmentservice.models.Appointment;
import com.tserashkevich.appointmentservice.models.enums.Status;
import com.tserashkevich.appointmentservice.repositories.AppointmentRepository;
import com.tserashkevich.appointmentservice.services.AppointmentGenerateService;
import com.tserashkevich.appointmentservice.services.AppointmentService;
import com.tserashkevich.appointmentservice.utils.AppointmentSortList;
import com.tserashkevich.appointmentservice.utils.LogList;
import com.tserashkevich.appointmentservice.utils.QueryPredicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final MongoTemplate mongoTemplate;
    private final ExternalServiceClient externalServiceClient;
    private final AppointmentGenerateService appointmentGenerateService;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<AppointmentResponse> findAll(AppointmentFindAllParams appointmentFindAllParams) {
        Pageable pageable = PageRequest.of(appointmentFindAllParams.getPage(), appointmentFindAllParams.getLimit(), appointmentFindAllParams.getSort());
        Query query = QueryPredicate.builder()
                .add(appointmentFindAllParams.getStatus(),
                        Criteria.where("status").is(appointmentFindAllParams.getStatus()))
                .add(appointmentFindAllParams.getDateStart(),
                        Criteria.where("date").gte(appointmentFindAllParams.getDateStart()))
                .add(appointmentFindAllParams.getDateEnd(),
                        Criteria.where("date").lte(appointmentFindAllParams.getDateEnd()))
                .add(appointmentFindAllParams.getPriceStart(),
                        Criteria.where("price").gte(appointmentFindAllParams.getPriceStart()))
                .add(appointmentFindAllParams.getPriceEnd(),
                        Criteria.where("price").lte(appointmentFindAllParams.getPriceEnd()))
                .with(pageable)
                .build();

        Page<Appointment> appointmentPage = PageableExecutionUtils.getPage(mongoTemplate.find(query, Appointment.class),
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1),
                        Appointment.class));

        log.info(LogList.FIND_ALL_APPOINTMENTS);
        return PageResponse.<AppointmentResponse>builder()
                .objectList(appointmentMapper.toResponses(appointmentPage.getContent()))
                .totalElements(appointmentPage.getTotalElements())
                .totalPages(appointmentPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public AppointmentResponse findById(String appointmentId) {
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
    public AppointmentResponse free(String appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);
        checkAppointmentNotCompleted(appointment);
        checkAppointmentNotNoShow(appointment);

        appointment.setPatient(null);
        appointment.setStatus(Status.FREE);
        appointmentRepository.save(appointment);

        appointmentGenerateService.regenerateAppointments(appointment.getDoctorWorkDayId());
        appointmentGenerateService.generateTodayAppointments();

        log.info(LogList.FREE_APPOINTMENT, appointment);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse book(String appointmentId, UUID patientId, UUID serviceId) {
        externalServiceClient.getPatient(patientId);
        Appointment appointment = getOrThrow(appointmentId);
        checkAppointmentNotCompleted(appointment);
        checkAppointmentNotNoShow(appointment);

        appointment.setPatient(patientId);
        appointment.setStatus(Status.BOOKED);
        appointment.setService(appointment.getService().stream()
                .filter(service -> service.getId().equals(serviceId))
                .toList());
        appointmentRepository.save(appointment);

        appointmentGenerateService.regenerateAppointments(appointment.getDoctorWorkDayId());
        appointmentGenerateService.generateTodayAppointments();

        log.info(LogList.BOOK_APPOINTMENT, appointment, patientId);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse checkIn(String appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);
        checkAppointmentNotCompleted(appointment);
        checkAppointmentNotNoShow(appointment);

        appointment.setStatus(Status.CHECKED_IN);
        appointmentRepository.save(appointment);

        log.info(LogList.CHECK_IN_APPOINTMENT, appointment);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse inProgress(String appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);
        checkAppointmentNotCompleted(appointment);
        checkAppointmentNotNoShow(appointment);

        appointment.setStatus(Status.IN_PROGRESS);
        appointmentRepository.save(appointment);

        log.info(LogList.IN_PROGRESS_APPOINTMENT, appointment);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse complete(String appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);
        checkAppointmentNotCompleted(appointment);
        checkAppointmentNotNoShow(appointment);

        appointment.setStatus(Status.COMPLETED);
        appointmentRepository.save(appointment);

        log.info(LogList.COMPLETE_APPOINTMENT, appointmentId);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse noShow(String appointmentId) {
        Appointment appointment = getOrThrow(appointmentId);
        checkAppointmentNotCompleted(appointment);
        checkAppointmentNotNoShow(appointment);

        appointment.setStatus(Status.NO_SHOW);
        appointmentRepository.save(appointment);

        log.info(LogList.NO_SHOW_APPOINTMENT, appointment);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> findFreeWithDoctorId(UUID doctorId) {
        List<Appointment> appointments = appointmentRepository.findByStatusAndDoctor_IdAndDateGreaterThanEqual(Status.FREE, doctorId, LocalDateTime.now(), AppointmentSortList.DATE_ASC.getValue());
        return appointmentMapper.toResponses(appointments);
    }

    @Override
    public List<AppointmentResponse> findByPatientId(UUID patientId) {
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPatient(patientId);
        return appointmentMapper.toResponses(appointments);
    }

    @Override
    public List<AppointmentResponse> findByDoctorId(UUID doctorId) {
        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctor_Id(doctorId);
        return appointmentMapper.toResponses(appointments);
    }

    private Appointment getOrThrow(String appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        return optionalAppointment.orElseThrow(AppointmentNotFoundException::new);
    }

    private void checkAppointmentNotCompleted(Appointment appointment) {
        if (appointment.getStatus().equals(Status.COMPLETED)) {
            throw new AppointmentAlreadyCompletedException();
        }
    }

    private void checkAppointmentNotNoShow(Appointment appointment) {
        if (appointment.getStatus().equals(Status.NO_SHOW)) {
            throw new AppointmentAlreadyNoShowException();
        }
    }

    private BigDecimal countPrice(BigDecimal price, Integer experience) {
        return experience == null
                ? price
                : BigDecimal.valueOf(experience.doubleValue() / 100 + 1);
    }

}
