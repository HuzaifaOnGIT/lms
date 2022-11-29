package com.tyss.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.tyss.lms.dto.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Employee_temp")
public class EmployeeTemp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String employeeId;
	
	private int yop;
	private String highestDegree;
	private long totalExperience;
	
	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "primaryinfo_Id")
	private EmployeePrimaryInfo employeePrimaryInfo;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "secondaryinfo_Id")
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "education_Id")
	private List<EmployeeEducationInfo> educationInfos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "address_id")
	private List<EmployeeAddressInfo> addressInfos;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "bank_Id")
	private EmployeeBankDetail bankDetail;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "technical_Id")
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT) 
	@JoinColumn(name = "experience_Id")
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "contact_Id")
	private List<EmployeeContactInfo> contactInfos;
	
	private ApprovalStatus status;
	


}
