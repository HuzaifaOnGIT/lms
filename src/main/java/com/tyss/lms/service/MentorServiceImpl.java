package com.tyss.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.lms.dto.EmployeeStatus;
import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.entity.BatchDetails;
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

			findAll = batchRepository.findAll();
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
	public EmployeeEntity changeStatus(String employeeId, EmployeeStatus status) {
		String methodName = "changeStatus";
		EmployeeEntity entity = null;
		try {

			entity = new EmployeeEntity();

			Optional<EmployeeEntity> findByEmployeeId = employeeRepository.findByEmployeeId(employeeId);

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
	public EmployeeEntity searchEmployee(String employeeId) {
		String methodName = "searchEmployee";
		EmployeeEntity employee = null;
		try {

			Optional<EmployeeEntity> findByEmployeeId = employeeRepository.findByEmployeeId(employeeId);
			if (findByEmployeeId == null) {
				log.info(methodName, " Null value received ", findByEmployeeId);
				throw new RuntimeException("employee not found");

			}
			employee=findByEmployeeId.get();

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return employee;
	}

}
