package com.tserashkevich.catalogservice.controllers;

import com.tserashkevich.catalogservice.dtos.FindAllParams;
import com.tserashkevich.catalogservice.dtos.PageResponse;
import com.tserashkevich.catalogservice.dtos.ServiceRequest;
import com.tserashkevich.catalogservice.dtos.ServiceResponse;
import com.tserashkevich.catalogservice.models.enums.Specialization;
import com.tserashkevich.catalogservice.services.ServiceService;
import com.tserashkevich.catalogservice.utils.SortList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {
    private final ServiceService serviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse createService(@Valid @RequestBody ServiceRequest serviceRequest) {
        return serviceService.create(serviceRequest);
    }

    @PutMapping(value = "/{id}")
    public ServiceResponse updateService(@PathVariable UUID id,
                                         @Valid @RequestBody ServiceRequest serviceRequest) {
        return serviceService.update(id, serviceRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable UUID id) {
        serviceService.delete(id);
    }

    @GetMapping
    public PageResponse<ServiceResponse> findAllServices(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                         @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                         @RequestParam(defaultValue = "ID_ASC") SortList sort,
                                                         @RequestParam(required = false) Specialization specialization,
                                                         @RequestParam(required = false) BigDecimal priceStart,
                                                         @RequestParam(required = false) BigDecimal priceEnd) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .specialization(specialization)
                .priceStart(priceStart)
                .priceEnd(priceEnd)
                .build();
        return serviceService.findAll(findAllParams);
    }

    @GetMapping("/{id}")
    public ServiceResponse findServiceById(@PathVariable UUID id) {
        return serviceService.findById(id);
    }

    @GetMapping("/search/{searchLine}")
    public List<ServiceResponse> searchServices(@PathVariable String searchLine) {
        return serviceService.search(searchLine);
    }

    @GetMapping("/exist/{serviceId}")
    public Boolean exitsService(@PathVariable UUID serviceId) {
        return serviceService.exist(serviceId);
    }
}
