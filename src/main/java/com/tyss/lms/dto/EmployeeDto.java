package com.tyss.lms.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tyss.lms.entity.EmployeeAddressInfo;
import com.tyss.lms.entity.EmployeeBankDetail;
import com.tyss.lms.entity.EmployeeContactInfo;
import com.tyss.lms.entity.EmployeeEducationInfo;
import com.tyss.lms.entity.EmployeeExperienceInfo;
import com.tyss.lms.entity.EmployeePrimaryInfo;
import com.tyss.lms.entity.EmployeeSecondaryInfo;
import com.tyss.lms.entity.EmployeeTechnicalSkillsInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
//	private String employeeId;

	@NotNull
	@NotBlank
	private int yop;
	@NotNull
	@NotBlank
	private String highestDegree;
	@NotNull
	@NotBlank
	private long totalExperience;
	@NotNull
	private EmployeePrimaryInfo employeePrimaryInfo;
	@NotNull
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	@NotNull
	private List<EmployeeEducationInfo> educationInfos;
	@NotNull
	private List<EmployeeAddressInfo> addressInfos;
	@NotNull
	private EmployeeBankDetail bankDetail;
	@NotNull
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	@NotNull
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	@NotNull
	private List<EmployeeContactInfo> contactInfos;
	

}
