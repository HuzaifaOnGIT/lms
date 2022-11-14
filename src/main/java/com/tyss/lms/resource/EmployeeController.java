package com.tyss.lms.resource;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.EmployeeConstant;
import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.entity.EmployeeEntity;
import com.tyss.lms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addResume(@RequestBody EmployeeDto employeeDto) {

		EmployeeEntity addResume = employeeService.addEmployee(employeeDto);
		if (addResume != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_ADDED_SUCCESSFULLY, addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, "Failed to Save Data", addResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseMessage> updateResume(@RequestBody EmployeeDto employeeDto) {

		EmployeeEntity updateResume = employeeService.updateEmployee(employeeDto);
		if (updateResume != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, EmployeeConstant.EMPLOYEE_ADDED_SUCCESSFULLY, updateResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, "Failed to Save Data", updateResume);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

}
