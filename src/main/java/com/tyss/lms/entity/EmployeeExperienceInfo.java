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
@Table(name = "employee_experience_info")
public class EmployeeExperienceInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeExperienceInfoId;
	private String companyName;
	private float experience;
	private String dateOfJoining;
	private String dateOfRelieving;
	private String designation;
	private String location;
}
