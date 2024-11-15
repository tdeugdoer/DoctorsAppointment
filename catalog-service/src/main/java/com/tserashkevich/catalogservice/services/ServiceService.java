package com.tserashkevich.catalogservice.services;

import com.tserashkevich.catalogservice.dtos.FindAllParams;
import com.tserashkevich.catalogservice.dtos.PageResponse;
import com.tserashkevich.catalogservice.dtos.ServiceRequest;
import com.tserashkevich.catalogservice.dtos.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface ServiceService {
    ServiceResponse create(ServiceRequest serviceRequest);

    ServiceResponse update(UUID serviceId, ServiceRequest serviceRequest);

    void delete(UUID serviceId);

    PageResponse<ServiceResponse> findAll(FindAllParams findAllParams);

    ServiceResponse findById(UUID serviceId);

    List<ServiceResponse> search(String searchLine);

    Boolean exist(UUID serviceId);
}
