package com.tyss.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.GlobalSearchDTO;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.EmployeeEntity;
import com.tyss.lms.entity.MentorDetails;
import com.tyss.lms.repository.BatchRepository;
import com.tyss.lms.repository.EmployeeRepository;
import com.tyss.lms.repository.MentorDetailRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

	private BatchRepository batchRepository;

	private MentorDetailRepository mentorRepository;

	private EmployeeRepository employeeRepository;

	@Override
	public BatchDetails addBatch(BatchDto batchDto) {
		String methodName = "addBatch";
		BatchDetails entity = null;
		try {

			entity = new BatchDetails();
			BeanUtils.copyProperties(batchDto, entity);
			Optional<BatchDetails> findById = batchRepository.findById(entity.getBatchId());
			if (findById.isPresent()) {

				log.error(methodName, "Batch already exists with this Id", entity);
				throw new RuntimeException("Batch already exists with this Id");
			}

			entity = batchRepository.save(entity);
			if (entity == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Batch not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return entity;
	}

	@Override
	public BatchDetails updateBatch(BatchDto batchDto) {
		String methodName = "updateBatch";
		BatchDetails save = null;

		Optional<BatchDetails> findById = batchRepository.findByBatchId(batchDto.getBatchId());
		try {
			if (!findById.isPresent()) {
				throw new RuntimeException("Batch Not Found Select Correct BatchID");
			} else {
				findById.get().setBatchName(batchDto.getBatchName());
				findById.get().setEndDate(batchDto.getEndDate());
				findById.get().setMentorName(batchDto.getMentorName());
				findById.get().setStatus(batchDto.getStatus());
				findById.get().setTechnologies(batchDto.getTechnologies());
				findById.get().setStartDate(batchDto.getStartDate());

				save = batchRepository.save(findById.get());
			}
		} catch (RuntimeException e) {
			log.error(methodName + e.getMessage());
		}
		return save;
	}

	@Override
	public BatchDetails searchBatch(Long batchId, String batchName) {
		String methodName = "searchBatch";
		BatchDetails search = null;
		try {
			search = batchRepository.findByBatchIdOrBatchName(batchId, batchName);
			if (search == null) {
				throw new RuntimeException("Batch Not Found");
			}
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			;
		}
		return search;
	}

	@Override
	public void deleteBatch(Long id) {
		try {
			Optional<BatchDetails> findById = batchRepository.findById(id);
			if (!findById.isPresent()) {
				throw new RuntimeException("Batch Details Not Present On This ID");
			} else {
				batchRepository.delete(findById.get());
				;
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MentorDetails updateMentor(MentorDto mentorDto) {
		String methodName = "updateBatch";
		MentorDetails save = null;

		Optional<MentorDetails> findById = mentorRepository.findByEmployeeId(mentorDto.getEmployeeId());
		try {
			if (findById.isEmpty()) {
				throw new RuntimeException("Mentor Not Found Select Correct BatchID");
			} else {
				findById.get().setEmailId(mentorDto.getEmailId());
				findById.get().setMentorName(mentorDto.getMentorName());
				findById.get().setSkills(mentorDto.getSkills());

				save = mentorRepository.save(findById.get());
			}
		} catch (RuntimeException e) {
			log.error(methodName + e.getMessage());
		}
		return save;
	}

	@Override
	public MentorDetails searchMentor(String employeeId, String mentorName) {
		String methodName = "searchMentor";
		MentorDetails search = null;
		try {
			search = batchRepository.findByEmployeeIdOrMentorName(employeeId, mentorName);
			if (search == null) {
				throw new RuntimeException("Mentor Not Found");
			}
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			;
		}
		return search;
	}

	@Override
	public void deleteMentor(String id) {
		try {
			Optional<MentorDetails> findById = mentorRepository.findByEmployeeId(id);
			if (!findById.isPresent()) {
				throw new RuntimeException("Mentor Details Not Present On This ID");
			} else {
				mentorRepository.deleteByEmployeeId(id);
				;
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MentorDetails addMentor(MentorDto mentorDto) {
		String methodName = "addMentor";
		MentorDetails entity = null;
		try {

			entity = new MentorDetails();
			BeanUtils.copyProperties(mentorDto, entity);

			entity = mentorRepository.save(entity);
			if (entity == null) {
				log.info(methodName, "==========> Null value received ", entity);
				throw new RuntimeException("Mentor not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return entity;
	}

	@Override
	public GlobalSearchDTO globalSearch(String parameter) {
		String methodName = "globalSearch";
		GlobalSearchDTO searchResult = new GlobalSearchDTO();
		try {
			List<EmployeeEntity> findAllByEmployeeId = employeeRepository.findAll();

			findAllByEmployeeId.forEach(e -> {
				if (e.getEmployeePrimaryInfo().getEmployeeName().equalsIgnoreCase(parameter)) {
					searchResult.getEmployees().add(e);
				}
			});

			if (searchResult.getEmployees().size() == 0) {
				findAllByEmployeeId.forEach(e -> {
					if (e.getEmployeeId().equalsIgnoreCase(parameter)) {
						searchResult.getEmployees().add(e);
					}
				});

				List<MentorDetails> findAllByEmployeeIdOrMentorName = mentorRepository
						.findAllByEmployeeIdOrMentorName(parameter, parameter);
				if (findAllByEmployeeIdOrMentorName != null) {
					searchResult.setMentors(findAllByEmployeeIdOrMentorName);
				}
			}
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			;
		}
		return searchResult;
	}

}
