package com.tyss.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Page<Employee> findByEmployeeId(String employeeId , Pageable pageable);

	public Optional<Employee> findByEmployeeId(String employeeId);
	
	
	public List <Employee > findAllByEmployeeIdContainingIgnoreCaseOrBatchNameContainingIgnoreCase(String param ,String param2 ,Pageable page);
	
	public Optional<List<Employee>> findAllByBatchId(long batchId);


}
