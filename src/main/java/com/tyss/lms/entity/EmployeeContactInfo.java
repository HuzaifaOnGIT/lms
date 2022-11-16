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
@Table(name = "employee_contact_info")
public class EmployeeContactInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeContactInfoId;
	private String contactType;
	private long contactNo;
}
