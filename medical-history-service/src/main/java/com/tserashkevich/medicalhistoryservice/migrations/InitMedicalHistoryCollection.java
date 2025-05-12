package com.tserashkevich.medicalhistoryservice.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
@ChangeUnit(id = "init-medical-history", order = "1", author = "tdeugdoer")
public class InitMedicalHistoryCollection {
    private final MongoTemplate mongoTemplate;
    private final String collectionName = "medical_history";

    @Execution
    public void changeSet() {
        mongoTemplate.createCollection(collectionName);
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.getCollection(collectionName).drop();
    }

}
