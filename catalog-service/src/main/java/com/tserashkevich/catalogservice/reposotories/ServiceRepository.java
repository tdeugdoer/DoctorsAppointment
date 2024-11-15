package com.tserashkevich.catalogservice.reposotories;

import com.tserashkevich.catalogservice.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID>, QuerydslPredicateExecutor<Service> {
}
