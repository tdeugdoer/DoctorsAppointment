package com.tserashkevich.appointmentservice.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
@ChangeUnit(id = "init-appointment", order = "1", author = "tdeugdoer")
public class InitAppointmentCollection {
    private final MongoTemplate mongoTemplate;
    private final String collectionName = "appointment";

    @Execution
    public void changeSet() {
        mongoTemplate.createCollection(collectionName);
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.getCollection(collectionName).drop();
    }

}
