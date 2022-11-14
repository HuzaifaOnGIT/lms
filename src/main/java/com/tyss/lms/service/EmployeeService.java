package com.tyss.lms.service;

import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.entity.EmployeeEntity;

public interface EmployeeService {

	public EmployeeEntity addEmployee(EmployeeDto employeeDto);

	public EmployeeEntity updateEmployee(EmployeeDto employeeDto);

	
}
