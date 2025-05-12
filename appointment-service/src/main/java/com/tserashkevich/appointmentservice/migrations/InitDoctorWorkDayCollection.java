package com.tserashkevich.appointmentservice.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
@ChangeUnit(id = "init-doctor-work-day", order = "2", author = "tdeugdoer")
public class InitDoctorWorkDayCollection {
    private final MongoTemplate mongoTemplate;
    private final String collectionName = "doctor_work_days";

    @Execution
    public void changeSet() {
        mongoTemplate.createCollection(collectionName);
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.getCollection(collectionName).drop();
    }

}
