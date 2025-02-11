package com.tserashkevich.medicalhistoryservice;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableMongock
@EnableFeignClients
@SpringBootApplication
public class MedicalHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalHistoryServiceApplication.class, args);
    }

}
