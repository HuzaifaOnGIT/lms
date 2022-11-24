package com.tyss.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeInActive;
@Repository
public interface EmployeeInActiveRepository extends JpaRepository<EmployeeInActive, Long> {

	public Page<EmployeeInActive> findByEmployeeId(String employeeId , Pageable pageable);
	public Optional<EmployeeInActive> findByEmployeeId(String employeeId);

	public Page<Employee> findByEmployeeIdContainingIgnoreCase(String employeeId,Pageable page);
	
	
	public List<EmployeeInActive > findAllByEmployeeIdContainingIgnoreCaseOrBatchNameContainingIgnoreCase(String param ,String param2 ,Pageable page);
	public List<EmployeeInActive > findAllByEmployeeIdContainingIgnoreCaseOrBatchNameContaining(String param, String param2,Pageable page);
	
	public Optional<List<EmployeeInActive>> findAllByBatchId(long batchId);
//	public List<Employee> findAllByEmployeeIdContainingIgnoreCase(String parameter, Pageable paging);
//	public List<Employee> findAllByEmployeeIdContainingIgnoreCaseOrBatchNameContainingIgnoreCase(String parameter,String parameter2, Pageable paging);
	public Optional<EmployeeInActive> findByEmployeeIdAndBatchId(String employeeId, long batchId);
	
	
	


}
