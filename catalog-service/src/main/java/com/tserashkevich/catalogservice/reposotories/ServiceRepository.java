package com.tserashkevich.catalogservice.reposotories;

import com.tserashkevich.catalogservice.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID>, QuerydslPredicateExecutor<Service> {
    @Query("select e from Service e where lower(e.name) like lower(concat('%', :search, '%')) " +
            "or lower(e.specialization) like lower(concat('%', :search, '%'))" +
            "or lower(e.description) like lower(concat('%', :search, '%'))")
    List<Service> search(@Param("search") String searchLine);

}
