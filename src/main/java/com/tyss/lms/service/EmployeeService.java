package com.tyss.lms.service;

import java.util.Map;

import com.tyss.lms.dto.AttendanceEnum;
import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeTemp;

public interface EmployeeService {

	public EmployeeTemp addEmployee(EmployeeDto employeeDto);

	public EmployeeTemp updateEmployee(EmployeeDto employeeDto);

	public Map<AttendanceEnum, Integer> attendanceStats(String empId);

	public Map<String,Integer> mockStats(String empId);

	
}
