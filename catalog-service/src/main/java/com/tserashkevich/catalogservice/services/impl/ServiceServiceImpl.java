package com.tserashkevich.catalogservice.services.impl;

import com.querydsl.core.types.Predicate;
import com.tserashkevich.catalogservice.dtos.FindAllParams;
import com.tserashkevich.catalogservice.dtos.PageResponse;
import com.tserashkevich.catalogservice.dtos.ServiceRequest;
import com.tserashkevich.catalogservice.dtos.ServiceResponse;
import com.tserashkevich.catalogservice.exceptions.ServiceNotFoundException;
import com.tserashkevich.catalogservice.mappers.ServiceMapper;
import com.tserashkevich.catalogservice.models.QService;
import com.tserashkevich.catalogservice.models.Service;
import com.tserashkevich.catalogservice.reposotories.ServiceRepository;
import com.tserashkevich.catalogservice.services.ServiceService;
import com.tserashkevich.catalogservice.utils.LogList;
import com.tserashkevich.catalogservice.utils.QPredicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceResponse create(ServiceRequest serviceRequest) {
        Service service = serviceMapper.toModel(serviceRequest);

        serviceRepository.save(service);

        log.info(LogList.CREATE_SERVICE, service.getId());
        return serviceMapper.toResponse(service);

    }

    @Override
    public ServiceResponse update(UUID serviceId, ServiceRequest serviceRequest) {
        Service service = getOrThrow(serviceId);

        serviceMapper.updateModel(service, serviceRequest);

        log.info(LogList.EDIT_SERVICE, serviceId);
        return serviceMapper.toResponse(service);
    }

    @Override
    public void delete(UUID serviceId) {
        Service service = getOrThrow(serviceId);

        serviceRepository.delete(service);

        log.info(LogList.DELETE_SERVICE, serviceId);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ServiceResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Predicate predicate = QPredicates.builder()
                .add(findAllParams.getSpecialization(), QService.service.specialization::eq)
                .add(findAllParams.getPriceStart(), QService.service.price::goe)
                .add(findAllParams.getPriceEnd(), QService.service.price::lt)
                .build();

        Page<Service> servicePage = serviceRepository.findAll(predicate, pageable);
        List<ServiceResponse> serviceResponses = serviceMapper.toResponses(servicePage.getContent());

        log.info(LogList.FIND_ALL_SERVICES);
        return PageResponse.<ServiceResponse>builder()
                .objectList(serviceResponses)
                .totalElements(servicePage.getTotalElements())
                .totalPages(servicePage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceResponse findById(UUID serviceId) {
        Service service = getOrThrow(serviceId);
        log.info(LogList.FIND_SERVICE, serviceId);
        return serviceMapper.toResponse(service);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceResponse> search(String searchLine) {
        log.info(LogList.SEARCH_SERVICE, searchLine);
        return serviceMapper.toResponses(serviceRepository.search(searchLine));
    }

    @Override
    public Boolean exist(UUID serviceId) {
        log.info(LogList.EXIST_SERVICE, serviceId);
        return serviceRepository.existsById(serviceId);
    }

    public Service getOrThrow(UUID serviceId) {
        Optional<Service> optionalPassenger = serviceRepository.findById(serviceId);
        return optionalPassenger.orElseThrow(ServiceNotFoundException::new);
    }
}
