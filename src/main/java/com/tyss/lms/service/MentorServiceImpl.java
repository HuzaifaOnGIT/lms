package com.tyss.lms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyss.lms.customexception.LMSCustomException;
import com.tyss.lms.dto.AttendanceDto;
import com.tyss.lms.dto.EmployeeStatus;
import com.tyss.lms.dto.Gender;
import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.dto.PagingAndFilter;
import com.tyss.lms.dto.StatsDTO;
import com.tyss.lms.entity.AttendanceEntity;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeInActive;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;
import com.tyss.lms.repository.AttendanceRepo;
import com.tyss.lms.repository.BatchRepository;
import com.tyss.lms.repository.EmployeeInActiveRepository;
import com.tyss.lms.repository.EmployeeRepository;
import com.tyss.lms.repository.MockRatingRepository;
import com.tyss.lms.repository.MockRepository;
import com.tyss.lms.security.jwt.AuthTokenFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MentorServiceImpl implements MentorService {

	@Autowired
	private MockRepository mockRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private MockRatingRepository mockRatingRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	AuthTokenFilter userDetailService;
	
	@Autowired
	private EmployeeInActiveRepository employeeInActiveRepository;

	@Autowired
	private AttendanceRepo attendanceRepo;

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
			Optional<Employee> employee = employeeRepository.findByEmployeeIdAndBatchId(entity.getEmployeeId(),
					entity.getBatchId());

			if (employee.isEmpty()) {
				throw new LMSCustomException(methodName + " No employee found for the Given Detail");
			}
			Optional<List<MockRatings>> ratings = mockRatingRepository
					.findAllByEmployeeIdAndMockNo(entity.getEmployeeId(), entity.getMockNo());
			log.info(methodName + ratings.get());
			log.info(methodName + ratings.get().toString());
			if (ratings.isPresent()) {
				if (ratings.get().size() > 0)
//				if(ratings.get().getEmployeeId()==entity.getEmployeeId()) {
					throw new RuntimeException("MockRatings Already Added for the Given Employee");
//				}
			}

			entity.setOverallFeedback(mockRatingDto.getOverallFeedback().name());
			entity = mockRatingRepository.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Mock not saved");

			}

		} catch (LMSCustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}

	@Override
	public Employee changeStatus(String employeeId, EmployeeStatus status) {
		String methodName = "changeStatus";
		Employee entity = null;
		EmployeeInActive inActive=null;
		try {

			entity = new Employee();
			switch (status) {
			case absconded:
			case terminated:
				Optional<Employee> findByEmployeeId = employeeRepository.findByEmployeeId(employeeId);

				if (findByEmployeeId.isPresent()) {
					entity = findByEmployeeId.get();
					entity.getEmployeePrimaryInfo().setStatus(status);
					inActive=new EmployeeInActive();
					BeanUtils.copyProperties(entity, inActive);

					inActive=employeeInActiveRepository.save(inActive);
					
					if (inActive==null ) {
						throw new RuntimeException("Failed to Change Status");
						
					}
					else {
						employeeRepository.delete(entity); 
					}
				} else {
					log.error(methodName + "findByEmployeeId returned====>" + findByEmployeeId);
					throw new RuntimeException("No Employee Found for the given data");
				}
				break;
			case active:
				Optional<EmployeeInActive> findByEmployeeId2 = employeeInActiveRepository.findByEmployeeId(employeeId);

				if (findByEmployeeId2.isPresent()) {
					inActive = findByEmployeeId2.get();
					entity.getEmployeePrimaryInfo().setStatus(status);
					entity=new Employee();
					BeanUtils.copyProperties(inActive,entity);

					entity=employeeRepository.save(entity);
					
					if (entity!=null && entity.getBankDetail().getAccountType()!=null) {
						throw new RuntimeException("Failed to Change Status");
						
					}
					else {
						employeeInActiveRepository.delete(inActive); 
					}
				} else {
					log.error(methodName + "findByEmployeeId returned====>" + findByEmployeeId2);
					throw new RuntimeException("No Employee Found for the given data");
				}
			default:
				
				break;
			}

		

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return entity;

	}

	@Override
	public List<Employee> searchEmployee(PagingAndFilter filter) {
		String methodName = "searchEmployee";
		List<Employee> employee = null;
		Pageable paging = null;
		try {
			employee=new ArrayList<>();
			paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
			log.info("filter"+filter);	
			log.info("paging"+paging);	
			Page<Employee> findByEmployeeId = employeeRepository
					.findByEmployeeIdContainingIgnoreCase(filter.getParameter(), paging);
			employee = findByEmployeeId.toList();
			log.info(methodName + findByEmployeeId);
			log.info(methodName + findByEmployeeId.getContent());
			if (employee == null) {
				log.info(methodName, " Null value received ", findByEmployeeId);
				throw new LMSCustomException("employee not found");

			}
		} catch (LMSCustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return employee;
	}

	@Override
	public StatsDTO genderStats(long batchId) {
		String methodName = "genderStats";
		List<EmployeeTemp> employees = null;
		StatsDTO genderStatsDTO = new StatsDTO();
		List<Map<Gender, Float>> genderDetail = new ArrayList<>();
		try {

			Optional<List<Employee>> findByBatchId = employeeRepository.findAllByBatchId(batchId);
			if (findByBatchId.isEmpty()) {
				log.info(methodName, " Null value received ", findByBatchId);
				throw new LMSCustomException("employee not found");
			}
			List<Employee> employeeList = findByBatchId.get();


			HashMap<Gender, Float> result = new HashMap<>();
			HashMap<Gender, Employee> result1 = new HashMap<>();
			Map<Gender, List<Employee>> resultMap = new HashMap<>();
			log.info(employeeList.toString());
			log.info("" + employeeList.size());
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				List<Employee> employeeSubList = resultMap.getOrDefault(employee.getEmployeePrimaryInfo().getGender(),
						new ArrayList<Employee>());
				employeeSubList.add(employee);
				log.info(methodName + "" + Float.valueOf(employeeSubList.size()));
				log.info(methodName + "" + employeeList.size());
				log.info(methodName + "" + employeeSubList.size() / employeeList.size());
				Float percentage = (Float.valueOf(employeeSubList.size()) / Float.valueOf(employeeList.size())) * 100f;
				result.put(employee.getEmployeePrimaryInfo().getGender(), percentage);
				result1.put(employee.getEmployeePrimaryInfo().getGender(), employee);
				resultMap.put(employee.getEmployeePrimaryInfo().getGender(), employeeSubList);

			}
			resultMap.forEach((k, v) -> {
				Float percentage = (Float.valueOf(v.size()) / Float.valueOf(employeeList.size())) * 100f;
				result.put(k, percentage);
			});

			genderDetail.add(result);
			genderStatsDTO.setGenderDetail(genderDetail);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return genderStatsDTO;
	}

	@Override
	public StatsDTO yopStats(long batchId) {
		String methodName = "batchPerformance";
//		Map<String, List<MockRatings>> result = null;
		StatsDTO yopStatsDTO = new StatsDTO();
//		List<Map<String,Integer> >res=new ArrayList<>();
		Map<Integer,Integer> sres=new HashMap<>();
		try {

			Optional<List<Employee>> findByBatchId = employeeRepository.findAllByBatchId(batchId);
			if (findByBatchId.isEmpty()) {
				log.info(methodName, " Null value received ", findByBatchId);
				throw new LMSCustomException("No Records Found");
			}
 			List<Employee> employees = findByBatchId.get();
			log.info(methodName + employees.toString());
			log.info(methodName + "=======>" + employees.size());

//			findAllByStudent_Grades_ClassName(final String className);


//			Map<String, List<MockRatings>> collect = 
					Map<Integer, List<Employee>> collect = employees.stream()
					.collect(Collectors.groupingBy(Employee::getYop));

			collect.forEach((k,v)->{
				sres.put(k, v.size());
//				res.add(sres)		;	
				});

			yopStatsDTO.setYopDetail(sres);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}

		return yopStatsDTO;
	}

	@Override
	public StatsDTO batchPerformance(long batchId) {
		String methodName = "batchPerformance";
		Map<String, List<MockRatings>> result = null;
		StatsDTO performanceStatsDTO = new StatsDTO();
//		List<Map<String,Integer> >res=new ArrayList<>();
		Map<String,Integer> sres=new HashMap<>();
		try {

			Optional<List<MockRatings>> findByBatchId = mockRatingRepository.findAllByBatchId(batchId);
			if (findByBatchId.isEmpty()) {
				log.info(methodName, " Null value received ", findByBatchId);
				throw new LMSCustomException("No Records Found");
			}
 			List<MockRatings> mockRatings = findByBatchId.get();
			log.info(methodName + mockRatings.toString());
			log.info(methodName + "=======>" + mockRatings.size());

//			findAllByStudent_Grades_ClassName(final String className);

			result = new HashMap<>();

			Map<String, List<MockRatings>> collect = mockRatings.stream()
					.collect(Collectors.groupingBy(MockRatings::getOverallFeedback));

			collect.forEach((k,v)->{
				sres.put(k, v.size());
//				res.add(sres)		;	
				});

			performanceStatsDTO.setPerformance(sres);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}

		return performanceStatsDTO;
	}

	@Override
	public StatsDTO experienceStats(long batchId) {
		
			String methodName = "experienceStats";
			StatsDTO expStatsDTO = new StatsDTO();
//			List<Map<String,Integer> >res=new ArrayList<>();
			Map<Long,Integer> sres=new HashMap<>();
			try {

				Optional<List<Employee>> findByBatchId = employeeRepository.findAllByBatchId(batchId);
				if (findByBatchId.isEmpty()) {
					log.info(methodName, " Null value received ", findByBatchId);
					throw new LMSCustomException("No Records Found");
				}
	 			List<Employee> employees = findByBatchId.get();
				log.info(methodName + employees.toString());
				log.info(methodName + "=======>" + employees.size());

//				findAllByStudent_Grades_ClassName(final String className);


//				Map<String, List<MockRatings>> collect = 
						Map<Long, List<Employee>> collect = employees.stream()
						.collect(Collectors.groupingBy(Employee::getTotalExperience));

				collect.forEach((k,v)->{
					sres.put(k, v.size());
//					res.add(sres)		;	
					});

				expStatsDTO.setExperienceDetail(sres);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(methodName + e.getMessage());
			}

			return expStatsDTO;
		}

	@Override
	public StatsDTO degreeStats(long batchId) {
		String methodName = "experienceStats";
		StatsDTO degreeStatsDTO = new StatsDTO();
//		List<Map<String,Integer> >res=new ArrayList<>();
		Map<String,Integer> sres=new HashMap<>();
		try {

			Optional<List<Employee>> findByBatchId = employeeRepository.findAllByBatchId(batchId);
			if (findByBatchId.isEmpty()) {
				log.info(methodName, " Null value received ", findByBatchId);
				throw new LMSCustomException("No Records Found");
			}
 			List<Employee> employees = findByBatchId.get();
			log.info(methodName + employees.toString());
			log.info(methodName + "=======>" + employees.size());

//			findAllByStudent_Grades_ClassName(final String className);


//			Map<String, List<MockRatings>> collect = 
					Map<String, List<Employee>> collect = employees.stream()
					.collect(Collectors.groupingBy(Employee::getHighestDegree));

			collect.forEach((k,v)->{
				sres.put(k, v.size());
//				res.add(sres)		;	
				});

			log.info( "sres==>"+sres);
			degreeStatsDTO.setDegreeStats(sres);
			log.info( "degreeStatsDTO==>"+degreeStatsDTO);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}

		return degreeStatsDTO;	}

	@Override
	public AttendanceEntity addAttendance(AttendanceDto attendanceDto) {
		String methodName = "addAttendance";
		AttendanceEntity entity = null;
		try {

			entity = new AttendanceEntity();
			BeanUtils.copyProperties(attendanceDto, entity);

			String employeeId = attendanceDto.getEmployeeId();
			Optional<Employee> findByEmployeeId = employeeRepository.findByEmployeeId(employeeId);
			if (findByEmployeeId.isEmpty()) {
				throw new RuntimeException("Employee Not Found");
			}
			if (attendanceDto.getBatchId() != findByEmployeeId.get().getBatchId()) {
				throw new RuntimeException("Please Enter Correct batch Id for the Given Employee");
			}
			SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
			String format = dateOnly.format(attendanceDto.getDate());
			AttendanceEntity attendance= attendanceRepo.findByEmployeeIdAndDate(employeeId,format);
			if(attendance!=null) {
				attendance.setAttendance(attendanceDto.getAttendance());
				log.info("Formatted Date"
						+ ""+format);
				attendance.setDate(format);
				entity = attendanceRepo.save(attendance);
				return entity;
			}
//			SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
			dateOnly.format(attendanceDto.getDate());
			entity.setDate(dateOnly.format(attendanceDto.getDate()));
			entity = attendanceRepo.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Mock not saved");

			}

		} catch (Exception e) {

			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}

	@Override
	public List<AttendanceEntity> getAttendance(String empId) {
		String methodName = "getAttendance";
		List<AttendanceEntity> entity = null;
		try {

			entity = new ArrayList<AttendanceEntity>();

			Optional<Employee> findByEmployeeId = employeeRepository.findByEmployeeId(empId);
			if (findByEmployeeId.isEmpty()) {
				throw new RuntimeException("Employee Not Found");
			}

			entity = attendanceRepo.findByEmployeeId(empId);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Mock not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}
}
