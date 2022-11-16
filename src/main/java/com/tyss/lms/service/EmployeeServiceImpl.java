package com.tyss.lms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.entity.EmployeeEntity;
import com.tyss.lms.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeEntity addEmployee(EmployeeDto employeeDto) {
		String methodName = "addEmployee";
		EmployeeEntity entity = null;

		try {

			entity = new EmployeeEntity();
			BeanUtils.copyProperties(employeeDto, entity);
			Optional<EmployeeEntity> findByEmployeeId = employeeRepository.findByEmployeeId(entity.getEmployeeId());
			
			if (findByEmployeeId.isPresent()) {
				log.error(methodName, "EmployeeId already exists", entity);
				throw new RuntimeException("EmployeeId already exists");
			}

			entity = employeeRepository.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Resume not saved");

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
	public EmployeeEntity updateEmployee(EmployeeDto employeeDto) {
		String methodName = "updateEmployeee";
		EmployeeEntity save = null;

		Optional<EmployeeEntity> findById = employeeRepository.findByEmployeeId(employeeDto.getEmployeeId());
		try {
			if (!findById.isPresent()) {
				throw new RuntimeException("Employee Not Found Select Correct Employee ID");
			} else {
				findById.get().setEmployeePrimaryInfo(employeeDto.getEmployeePrimaryInfo());
				findById.get().setEmployeeSecondaryInfo(employeeDto.getEmployeeSecondaryInfo());
				findById.get().setEmployeeTechnicalSkillsInfo(employeeDto.getEmployeeTechnicalSkillsInfo());
				findById.get().setAddressInfos(employeeDto.getAddressInfos());
				findById.get().setBankDetail(employeeDto.getBankDetail());
				findById.get().setEmployeeExperienceInfos(employeeDto.getEmployeeExperienceInfos());
				findById.get().setEducationInfos(employeeDto.getEducationInfos());
				findById.get().setContactInfos(employeeDto.getContactInfos());
				save = employeeRepository.save(findById.get());

			}
		} catch (RuntimeException e) {
			log.error(methodName + e.getMessage());
		}
		return save;
	}
}
