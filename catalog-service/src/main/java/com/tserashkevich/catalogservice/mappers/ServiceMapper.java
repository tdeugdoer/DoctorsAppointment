package com.tserashkevich.catalogservice.mappers;

import com.tserashkevich.catalogservice.dtos.ServiceRequest;
import com.tserashkevich.catalogservice.dtos.ServiceResponse;
import com.tserashkevich.catalogservice.models.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {
    @Mapping(target = "id", ignore = true)
    Service toModel(ServiceRequest createServiceRequest);

    ServiceResponse toResponse(Service service);

    List<ServiceResponse> toResponses(List<Service> services);

    @Mapping(target = "id", ignore = true)
    void updateModel(@MappingTarget Service service, ServiceRequest serviceRequest);

}
