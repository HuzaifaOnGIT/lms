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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Employee_InActive")
public class EmployeeInActive implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inactiveId;
	
	private String employeeId;

	private int yop;
	
	private String highestDegree;
	
	private long totalExperience;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "primary_id")
	private EmployeePrimaryInfo employeePrimaryInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "secondary_id")
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "education_id")
	private List<EmployeeEducationInfo> educationInfos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "emp_address_id")
	private List<EmployeeAddressInfo> addressInfos;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "bank_id")
	private EmployeeBankDetail bankDetail;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "tenicalskills_id")
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "experience_id")
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "contact_id")
	private List<EmployeeContactInfo> contactInfos;
	

	private long batchId;
	
	

	private String batchName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BatchDetails_id")
	private BatchDetails batchDetails;
	
	

}
