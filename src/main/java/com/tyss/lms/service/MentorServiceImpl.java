package com.tyss.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.lms.customexception.LMSCustomException;
import com.tyss.lms.dto.EmployeeStatus;
import com.tyss.lms.dto.Gender;
import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.dto.StatsDTO;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeEntity;
import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;
import com.tyss.lms.repository.BatchRepository;
import com.tyss.lms.repository.EmployeeRepository;
import com.tyss.lms.repository.MockRatingRepository;
import com.tyss.lms.repository.MockRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

	@Autowired
	private MockRepository mockRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private MockRatingRepository mockRatingRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public MockDetails addMock(MockDetailDto mockDto) {
		String methodName = "addMock";
		MockDetails entity = null;
		try {

			entity = new MockDetails();
			BeanUtils.copyProperties(mockDto, entity);

			entity = mockRepository.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Mock not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return entity;
	}

	@Override
	public List<BatchDetails> viewBatch() {

		String methodName = "viewBatch";
		List<BatchDetails> findAll = null;
		try {

			findAll = (List<BatchDetails>) batchRepository.findAll();
			if (findAll == null) {
				log.info(methodName, " Null value received ", findAll);
				throw new RuntimeException("Mock not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return findAll;
	}

	@Override
	public MockRatings rateMock(MockRatingDto mockRatingDto) {
		String methodName = "rateMock";
		MockRatings entity = null;
		try {

			entity = new MockRatings();
			BeanUtils.copyProperties(mockRatingDto, entity);

			entity = mockRatingRepository.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Mock not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return entity;
	}

	@Override
	public Employee changeStatus(String employeeId, EmployeeStatus status) {
		String methodName = "changeStatus";
		Employee entity = null;
		try {

			entity = new Employee();

			Optional<Employee> findByEmployeeId = employeeRepository.findByEmployeeId(employeeId);

			if (findByEmployeeId.isPresent()) {
				entity = findByEmployeeId.get();
				entity.getEmployeePrimaryInfo().setStatus(status);

				employeeRepository.save(entity);
			} else {
				log.error(methodName + "findByEmployeeId returned====>" + findByEmployeeId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return entity;

	}

	@Override
	public Employee searchEmployee(String employeeId) {
		String methodName = "searchEmployee";
		Employee employee = null;
		try {

			Optional<Employee> findByEmployeeId = employeeRepository.findByEmployeeId(employeeId);
			if (findByEmployeeId == null) {
				log.info(methodName, " Null value received ", findByEmployeeId);
				throw new RuntimeException("employee not found");

			}
			employee = findByEmployeeId.get();

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return employee;
	}

	@Override
	public StatsDTO genderStats(long batchId) {
		String methodName = "genderStats";
		List<EmployeeEntity> employees = null;
		StatsDTO genderStatsDTO = new StatsDTO();
		try {

			Optional<BatchDetails> findById = batchRepository.findById(batchId);
			if (findById.isEmpty()) {
				log.info(methodName, " Null value received ", findById);
				throw new LMSCustomException("employee not found");
			}
			BatchDetails batchDetails = findById.get();
			List<Employee> employeeList = batchDetails.getEmployee();

//			findAllByStudent_Grades_ClassName(final String className);

			HashMap<Gender, Float> result = new HashMap<>();
			Map<Gender, List<Employee>> resultMap = new HashMap<>();
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				List<Employee> employeeSubList = resultMap.getOrDefault(employee.getEmployeePrimaryInfo().getGender(),
						new ArrayList<Employee>());
				employeeSubList.add(employee);
				Float percentage = employeeSubList.size() / employeeList.size() * 100f;
				result.put(employee.getEmployeePrimaryInfo().getGender(), percentage);
			}

			genderStatsDTO.setGenderDetail(result);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return genderStatsDTO;
	}

	@Override
	public StatsDTO yopStats(long batchId) {
		String methodName = "genderStats";
		List<EmployeeEntity> employees = null;
		StatsDTO genderStatsDTO = new StatsDTO();
		try {

			Optional<BatchDetails> findById = batchRepository.findById(batchId);
			if (findById.isEmpty()) {
				log.info(methodName, " Null value received ", findById);
				throw new LMSCustomException("employee not found");
			}
			BatchDetails batchDetails = findById.get();
			List<Employee> employeeList = batchDetails.getEmployee();

//			findAllByStudent_Grades_ClassName(final String className);

			HashMap<Integer, Integer> result = new HashMap<>();
			Map<Integer, List<Employee>> resultMap = new HashMap<>();
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				List<Employee> employeeSubList = resultMap.getOrDefault(employee.getEmployeePrimaryInfo().getGender(),
						new ArrayList<Employee>());
				employeeSubList.add(employee);
				 resultMap.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(), employeeSubList);
				result.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(),
						employeeSubList.size());
			}

			genderStatsDTO.setYopDetail(result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}

		return genderStatsDTO;
	}

	@Override
	public StatsDTO batchPerformance(long batchId) {
		String methodName = "genderStats";
		List<EmployeeEntity> employees = null;
		StatsDTO genderStatsDTO = new StatsDTO();
		try {

			Optional<BatchDetails> findById = batchRepository.findById(batchId);
			if (findById.isEmpty()) {
				log.info(methodName, " Null value received ", findById);
				throw new LMSCustomException("employee not found");
			}
			BatchDetails batchDetails = findById.get();
			List<Employee> employeeList = batchDetails.getEmployee();

//			findAllByStudent_Grades_ClassName(final String className);

			HashMap<Integer, Integer> result = new HashMap<>();
			Map<Integer, List<Employee>> resultMap = new HashMap<>();
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				List<Employee> employeeSubList = resultMap.getOrDefault(employee.getEmployeePrimaryInfo().getGender(),
						new ArrayList<Employee>());
				employeeSubList.add(employee);
				 resultMap.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(), employeeSubList);
				result.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(),
						employeeSubList.size());
			}

			genderStatsDTO.setYopDetail(result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}

		return genderStatsDTO;
	}

	@Override
	public StatsDTO experienceStats(long batchId) {
		String methodName = "genderStats";
		List<EmployeeEntity> employees = null;
		StatsDTO experienceDetail = new StatsDTO();
//		try {

//			Optional<BatchDetails> findById = batchRepository.findById(batchId);
//			if (findById.isEmpty()) {
//				log.info(methodName, " Null value received ", findById);
//				throw new LMSCustomException("employee not found");
//			}
//			BatchDetails batchDetails = findById.get();
//			List<Employee> employeeList = batchDetails.getEmployee();
//
////			findAllByStudent_Grades_ClassName(final String className);
//
//			HashMap<Integer, Integer> result = new HashMap<>();
//			Map<Integer, List<Employee>> resultMap = new HashMap<>();
//			for (int i = 0; i < employeeList.size(); i++) {
//				Employee employee = employeeList.get(i);
//				List<Employee> employeeSubList = resultMap.getOrDefault(employee.getEmployeePrimaryInfo().getGender(),
//						new ArrayList<Employee>());
//				employeeSubList.add(employee);
//				 resultMap.put(employee.getEducationInfos().get(employee.getEmployeeExperienceInfos(), employeeSubList);
//				result.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(),
//						employeeSubList.size());
//			}
//
//			genderStatsDTO.setYopDetail(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(methodName + e.getMessage());
//		}

		return null;
	}

	@Override
	public StatsDTO degreeStats(long batchId) {
		String methodName = "genderStats";
		List<EmployeeEntity> employees = null;
		StatsDTO genderStatsDTO = new StatsDTO();
		try {

			Optional<BatchDetails> findById = batchRepository.findById(batchId);
			if (findById.isEmpty()) {
				log.info(methodName, " Null value received ", findById);
				throw new LMSCustomException("employee not found");
			}
			BatchDetails batchDetails = findById.get();
			List<Employee> employeeList = batchDetails.getEmployee();

//			findAllByStudent_Grades_ClassName(final String className);

			HashMap<Integer, Integer> result = new HashMap<>();
			Map<Integer, List<Employee>> resultMap = new HashMap<>();
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				List<Employee> employeeSubList = resultMap.getOrDefault(employee.getEmployeePrimaryInfo().getGender(),
						new ArrayList<Employee>());
				employeeSubList.add(employee);
				 resultMap.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(), employeeSubList);
				result.put(employee.getEducationInfos().get(employee.getEducationInfos().size()).getYearOfPassing(),
						employeeSubList.size());
			}

			genderStatsDTO.setYopDetail(result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}

		return genderStatsDTO;
	}
}
