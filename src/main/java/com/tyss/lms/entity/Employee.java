package com.tyss.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private EmployeePrimaryInfo employeePrimaryInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	
	private List<EmployeeEducationInfo> educationInfos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private List<EmployeeAddressInfo> addressInfos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private EmployeeBankDetail bankDetail;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private List<EmployeeContactInfo> contactInfos;
	
	@NotNull
	@NotBlank
	private long batchId;
	
	@NotNull
	@NotBlank
	private String batchName;
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "BatchDetails_id")
//	private BatchDetails batchDetails;
	
	

}
