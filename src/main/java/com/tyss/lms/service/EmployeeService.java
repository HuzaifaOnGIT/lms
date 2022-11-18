package com.tyss.lms.service;

import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.entity.EmployeeTemp;

public interface EmployeeService {

	public EmployeeTemp addEmployee(EmployeeDto employeeDto);

	public EmployeeTemp updateEmployee(EmployeeDto employeeDto);

	
}
