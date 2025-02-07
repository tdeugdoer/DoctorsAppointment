package com.tserashkevich.medicalhistoryservice.repositories;

import com.tserashkevich.medicalhistoryservice.models.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, UUID> {
}
