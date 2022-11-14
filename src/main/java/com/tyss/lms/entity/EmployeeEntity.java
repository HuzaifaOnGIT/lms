package com.tyss.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Employee")
public class EmployeeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String employeeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeePrimaryInfoId")
	private EmployeePrimaryInfo employeePrimaryInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeSecondaryInfoId")
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeEducationInfoId")
	private List<EmployeeEducationInfo> educationInfos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeAddressInfoId")
	private List<EmployeeAddressInfo> addressInfos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeBankDetailId")
	private EmployeeBankDetail bankDetail;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeTechnicalSkillsInfoId")
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeExperienceInfoId")
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeContactInfoId")
	private List<EmployeeContactInfo> contactInfos;

}
