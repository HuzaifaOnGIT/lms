package com.tyss.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tyss.lms.dto.EmployeeStatus;

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
	private int employeePrimaryInfoId;
	private String employeeId;
	private String employeeName;
	private String dateOfJoining;
	private String dob;
	private String emailId;
	private String bloodGroup;
	private String designation;
	private String gender;
	private String nationality;
	private EmployeeStatus status;

}
