package com.redeyefrog.persistence.repository;

import com.redeyefrog.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    List<CustomerEntity> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);

}
