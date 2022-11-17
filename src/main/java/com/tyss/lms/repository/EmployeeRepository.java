package com.tyss.lms.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.Employee;
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	Optional<Employee> findByEmployeeId(String employeeId , Pageable pageable);

	Optional<Employee> findByEmployeeId(String employeeId);


}
