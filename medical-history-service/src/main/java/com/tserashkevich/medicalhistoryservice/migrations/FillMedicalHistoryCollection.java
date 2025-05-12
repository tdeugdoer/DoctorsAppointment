package com.tserashkevich.medicalhistoryservice.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@ChangeUnit(id = "fill-medical-history", order = "2", author = "tdeugdoer")
public class FillMedicalHistoryCollection {
    private final MongoTemplate mongoTemplate;
    private final ObjectId objectId = new ObjectId("67b352357c265f8f660c8b02");
    private final String collectionName = "medical_history";

    @Execution
    public void changeSet() {
        Document medicalRecordsDocument = new Document()
                .append("_id", objectId)
                .append("patient", UUID.fromString("32fe92f0-5478-482b-b337-6b66652baa85"))
                .append("appointment", UUID.fromString("79ad3d28-ff30-458d-9eac-3117fa7e1775"))
                .append("doctor", UUID.fromString("586d191f-93f1-469b-8098-4e5901afd3e0"))
                .append("dateOfVisit", LocalDate.of(2024, 3, 10))
                .append("diagnosis", "Dental Caries")
                .append("treatments", List.of("Removal of the affected part of the tooth",
                        "Filling the tooth with composite material",
                        "Fluoridation of the tooth to strengthen the enamel"))
                .append("allergies", null)
                .append("recommendations", List.of("Regular oral hygiene (brushing twice a day)",
                        "Using dental floss to remove food debris",
                        "Visiting the dentist every 6 months",
                        "Reducing the consumption of sweets and carbonated drinks"))
                .append("fileKeys", null)
                .append("notes", null);
        mongoTemplate.insert(medicalRecordsDocument, collectionName);
    }

    @RollbackExecution
    public void rollback() {
        Query query = new Query(Criteria.where("_id").is(objectId));
        mongoTemplate.remove(query, collectionName);
    }

}
