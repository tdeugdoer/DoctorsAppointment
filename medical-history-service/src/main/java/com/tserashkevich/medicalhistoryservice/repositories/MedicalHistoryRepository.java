package com.tserashkevich.medicalhistoryservice.repositories;

import com.tserashkevich.medicalhistoryservice.models.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, UUID> {
}
