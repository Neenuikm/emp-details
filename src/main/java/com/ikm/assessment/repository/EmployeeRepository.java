package com.ikm.assessment.repository;

import com.ikm.assessment.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, UUID> {

    List<EmployeeDetails> findAllBySalaryLessThan(double salary);

    List<EmployeeDetails> findBySalaryBetween(double salary, double salary2);

    List<EmployeeDetails> findBySalaryGreaterThan(double salary);
}
