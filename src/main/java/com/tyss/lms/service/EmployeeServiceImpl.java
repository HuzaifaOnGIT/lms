package com.tyss.lms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.lms.dto.ApprovalStatus;
import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.repository.EmployeeTempRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeTempRepository employeeRepository;

	@Override
	public EmployeeTemp addEmployee(EmployeeDto employeeDto) {
		String methodName = "addEmployee";
		EmployeeTemp entity = null;

		try {

			entity = new EmployeeTemp();
			BeanUtils.copyProperties(employeeDto, entity);
			log.info(methodName +"employeeDto==>"+employeeDto);
			log.info(methodName +"entity     ==>"+employeeDto);
			entity.setEmployeeId(employeeDto.getEmployeePrimaryInfo().getEmployeeId());
			Optional<EmployeeTemp> findByEmployeeId = employeeRepository.findByEmployeeId(entity.getEmployeeId());
			
			if (findByEmployeeId.isPresent()) {
				log.error(methodName, "EmployeeId already exists", entity);
				throw new RuntimeException("EmployeeId already exists");
			}

			entity.setStatus(ApprovalStatus.approval_pending);
			
			entity = employeeRepository.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Employee not saved");

			}

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}

	@Override
	public EmployeeTemp updateEmployee(EmployeeDto employeeDto) {
		String methodName = "updateEmployeee";
		EmployeeTemp entity = null;

		Optional<EmployeeTemp> findById = employeeRepository.findByEmployeeId(employeeDto.getEmployeePrimaryInfo().getEmployeeId());
		try {
			if (findById.isEmpty()) {
				throw new RuntimeException("Employee Not Found Select Correct Employee ID");
			} else {
			
				EmployeeTemp employeeTemp = findById.get();
				
		
				log.info(methodName +"employeeDto=======>"+employeeDto);
				log.info(methodName +"employeeTemp======>"+employeeTemp);
				employeeTemp.setEmployeePrimaryInfo(employeeDto.getEmployeePrimaryInfo());
				employeeTemp.setEmployeeSecondaryInfo(employeeDto.getEmployeeSecondaryInfo());
				employeeTemp.setEmployeeTechnicalSkillsInfo(employeeDto.getEmployeeTechnicalSkillsInfo());
				employeeTemp.setAddressInfos(employeeDto.getAddressInfos());
				employeeTemp.setBankDetail(employeeDto.getBankDetail());
				employeeTemp.setEmployeeExperienceInfos(employeeDto.getEmployeeExperienceInfos());
				employeeTemp.setEducationInfos(employeeDto.getEducationInfos());
				employeeTemp.setContactInfos(employeeDto.getContactInfos());
				employeeTemp.setStatus(ApprovalStatus.approval_pending);
				entity = employeeRepository.save(employeeTemp);

			}
		} catch (RuntimeException e) {
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}
}
