package com.tyss.lms.dto;

import java.util.List;

import javax.persistence.Entity;

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
	private String employeeId;
	private EmployeePrimaryInfo employeePrimaryInfo;
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	private List<EmployeeEducationInfo> educationInfos;
	private List<EmployeeAddressInfo> addressInfos;
	private EmployeeBankDetail bankDetails;
	private List<EmployeeTechnicalSkillsInfo> employeeTechnicalSkillsInfo;
	private List<EmployeeExperienceInfo> employeeExperienceInfos;
	private List<EmployeeContactInfo> contactInfos;

}
