package com.tserashkevich.appointmentservice.feign;

import com.tserashkevich.appointmentservice.configs.feign.FeignConfig;
import com.tserashkevich.appointmentservice.dtos.feign.ServiceResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Retry(name = "retry-conf")
@CircuitBreaker(name = "circuitbreaker-conf")
@FeignClient(name = "service", configuration = FeignConfig.class)
public interface ServiceFeignClient {
    @GetMapping("/{serviceId}")
    ServiceResponse findService(@PathVariable UUID serviceId);
}
