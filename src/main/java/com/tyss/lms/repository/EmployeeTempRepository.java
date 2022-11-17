package com.tyss.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.EmployeeEntity;
@Repository
public interface EmployeeTempRepository extends JpaRepository<EmployeeEntity, Long> {


	Optional<EmployeeEntity> findByEmployeeId(String employeeId);


}
