package com.tyss.lms.resource;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.EmployeeConstant;
import com.tyss.lms.dto.AttendanceEnum;
import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<ResponseMessage> addResume(@Valid @RequestBody EmployeeDto employeeDto) {

		EmployeeTemp addResume=null;
		
			addResume = employeeService.addEmployee(employeeDto);
		
		
		if (addResume != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_ADD_SUCCESS, addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_ADD_FAIL, addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<ResponseMessage> updateResume(@Valid @RequestBody EmployeeDto employeeDto) {

		EmployeeTemp updateEmployee = employeeService.updateEmployee(employeeDto);
		if (updateEmployee != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_UPDATE_SUCCESS, updateEmployee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_UPDATE_FAIL, updateEmployee);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/dashboard/mock/{empId}")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<ResponseMessage> mockStats(@PathVariable String empId) {
		
		Map<String,Integer> result = employeeService.mockStats(empId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_SEARCH_SUCCESS, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_SEARCH_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/dashboard/attendance/{empId}")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<ResponseMessage> attendanceStats(@PathVariable String empId) {
		
		Map<AttendanceEnum,Integer> result = employeeService.attendanceStats(empId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_SEARCH_SUCCESS, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_SEARCH_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

}
