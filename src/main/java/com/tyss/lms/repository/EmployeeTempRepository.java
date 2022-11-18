package com.tyss.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.EmployeeTemp;
@Repository
public interface EmployeeTempRepository extends JpaRepository<EmployeeTemp, Long> {


	Optional<EmployeeTemp> findByEmployeeId(String employeeId);


}
