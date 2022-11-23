package com.tyss.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tyss.lms.dto.EmployeeStatus;
import com.tyss.lms.dto.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee_primary_Info")
public class EmployeePrimaryInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeePrimaryInfoId;
	@NotBlank(message="EmployeeId Required")
	@Size(max=15 ,message = "EmployeeId Should be less than 15 characters")
	@NotNull
	private String employeeId;
	@NotBlank(message="EmployeeId Required")
	@Size(max=25 ,message = "Employee Name Should be less than 25 characters")
	private String employeeName;
	private String dateOfJoining;
	private String dob;
	@Email(message = "Please Enter Valid Email Address")
	@Size(max=30 ,message = "Employee Name Should be less than 30 characters")
	private String emailId;
	private String bloodGroup;
	private String designation;
	private Gender gender;
	private String nationality;
	private EmployeeStatus status;

}
