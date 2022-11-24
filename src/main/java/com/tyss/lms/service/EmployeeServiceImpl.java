package com.tyss.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyss.lms.dto.ApprovalStatus;
import com.tyss.lms.dto.AttendanceEnum;
import com.tyss.lms.dto.EmployeeDto;
import com.tyss.lms.entity.AttendanceEntity;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.entity.MockRatings;
import com.tyss.lms.repository.AttendanceRepo;
import com.tyss.lms.repository.EmployeeTempRepository;
import com.tyss.lms.repository.MockRatingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	private static final String NO_RECORDS_FOUND = "No Records Found";

	@Autowired
	private EmployeeTempRepository employeeRepository;
	
	@Autowired
	private MockRatingRepository mockRatingRepository;
	
	@Autowired
	private AttendanceRepo attendanceRepo;
	
	

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

		try {
			Optional<EmployeeTemp> findById = employeeRepository.findByEmployeeId(employeeDto.getEmployeePrimaryInfo().getEmployeeId());
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

	@Override
	public Map<AttendanceEnum, Integer> attendanceStats(String empId) {
		String methodName = "attendanceStats";
		Map<AttendanceEnum,Integer> sres=new HashMap<>();

		try {
			List<AttendanceEntity> findById = attendanceRepo.findAllByEmployeeId(empId);
			if (findById==null || findById.size()==0) {
				throw new RuntimeException(NO_RECORDS_FOUND);
			} 
//			Map<String, List<MockRatings>> collect = 
					Map<AttendanceEnum, List<AttendanceEntity>> collect = findById.stream()
					.collect(Collectors.groupingBy(AttendanceEntity::getAttendance));

			collect.forEach((k,v)->{
				sres.put(k, v.size());
//				res.add(sres)		;	
				});

			log.info( "sres==>"+sres);
			
		} catch (RuntimeException e) {
			log.error(methodName + e.getMessage());
			throw e;
		}
		return sres;
	}

	@Override
	public Map<String,Integer> mockStats(String empId) {
		String methodName = "mockStats";
		Map<String,Integer> sres=new HashMap<>();

		try {
			Optional<List<MockRatings>> findById = mockRatingRepository.findAllByEmployeeId(empId);
			if (findById.isEmpty()) {
				throw new RuntimeException(NO_RECORDS_FOUND);
			} 
			List<MockRatings> list = findById.get();
//			Map<String, List<MockRatings>> collect = 
					Map<String, List<MockRatings>> collect = list.stream()
					.collect(Collectors.groupingBy(MockRatings::getOverallFeedback));

			collect.forEach((k,v)->{
				sres.put(k, v.size());
//				res.add(sres)		;	
				});

			log.info( "sres==>"+sres);
			
			
		} catch (RuntimeException e) {
			log.error(methodName + e.getMessage());
			throw e;
		}
		return sres;
	}
}
