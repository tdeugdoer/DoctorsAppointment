package com.tserashkevich.feedbackservice.feign;

import com.tserashkevich.feedbackservice.config.feign.FeignConfig;
import com.tserashkevich.feedbackservice.dtos.feign.AppointmentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Retry(name = "retry-conf")
@CircuitBreaker(name = "circuitbreaker-conf")
@FeignClient(name = "appointment", configuration = FeignConfig.class)
public interface AppointmentFeignClient {
    @GetMapping("/{appointmentId}")
    AppointmentResponse findAppointment(@PathVariable String appointmentId);

}
