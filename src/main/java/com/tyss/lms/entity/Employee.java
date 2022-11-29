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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table(name = "Employee")
public class Employee implements Serializable {

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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_primaryId")
	private EmployeePrimaryInfo employeePrimaryInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_secondaryId")
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "edu_Id")
	
	private List<EmployeeEducationInfo> educationInfos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "addr_Id")
	private List<EmployeeAddressInfo> addressInfos;
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name = "bnkId")
	private EmployeeBankDetail bankDetail;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "techId")
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "expId")
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "contact_id")
	private List<EmployeeContactInfo> contactInfos;
	
	@NotNull
	private long batchId;
	
	
	@NotNull
	@NotBlank
	private String batchName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BatchDetailsId")
	private BatchDetails batchDetails;
	
	

}
