package com.tyss.lms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.EmployeeInActive;
@Repository
public interface EmployeeInActiveRepository extends JpaRepository<EmployeeInActive, Long> {

	public Page<EmployeeInActive> findByEmployeeId(String employeeId , Pageable pageable);
	public Optional<EmployeeInActive> findByEmployeeId(String employeeId);

}