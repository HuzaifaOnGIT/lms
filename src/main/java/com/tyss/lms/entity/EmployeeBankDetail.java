package com.tyss.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tyss.lms.dto.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee_bank_details")
public class EmployeeBankDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeBankDetailId;
	private long accountNo;
	private String bankName;
	private AccountType accountType ;
	private String ifscCode;
	private String branch;
	private String state;
	

}
