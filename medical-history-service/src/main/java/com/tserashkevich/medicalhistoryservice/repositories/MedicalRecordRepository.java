package com.tserashkevich.medicalhistoryservice.repositories;

import com.tserashkevich.medicalhistoryservice.models.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    @Query("{ $or: [ { 'diagnosis': { $regex: ?0, $options: 'i' } }, { 'treatments': { $regex: ?0, $options: 'i' } }, { 'allergies': { $regex: ?0, $options: 'i' } }, { 'recommendations': { $regex: ?0, $options: 'i' } } ] }")
    List<MedicalRecord> findBySearchLine(String searchLine);
}
