package com.tyss.lms.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.EmployeeConstant;
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
	public ResponseEntity<ResponseMessage> addResume(@RequestBody EmployeeDto employeeDto) {

		EmployeeTemp addResume=null;
		try {
			addResume = employeeService.addEmployee(employeeDto);
		} catch (RuntimeException e) {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_ADD_FAIL+e.getMessage(), addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
		if (addResume != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_ADD_SUCCESS, addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_ADD_FAIL, addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<ResponseMessage> updateResume(@RequestBody EmployeeDto employeeDto) {

		EmployeeTemp updateResume = employeeService.updateEmployee(employeeDto);
		if (updateResume != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_UPDATE_SUCCESS, updateResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true,EmployeeConstant.EMPLOYEE_UPDATE_FAIL, updateResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

}
