package com.tyss.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee_education_info")
public class EmployeeEducationInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeEducationInfoId;
	private String educationType;
	private Integer yearOfPassing;
	private float percentage;
	private String universityName;
	private String instituteName;
	private String specilization;
	private String state;

}
