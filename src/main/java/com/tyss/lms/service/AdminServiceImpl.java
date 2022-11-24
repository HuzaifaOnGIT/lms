package com.tyss.lms.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyss.lms.customexception.LMSCustomException;
import com.tyss.lms.dto.ApprovalStatus;
import com.tyss.lms.dto.ApproveRejectDto;
import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.GlobalSearchDTO;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.dto.PagingAndFilter;
import com.tyss.lms.dto.SortingOrder;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.entity.MentorDetails;
import com.tyss.lms.repository.BatchRepository;
import com.tyss.lms.repository.EmployeeRepository;
import com.tyss.lms.repository.EmployeeTempRepository;
import com.tyss.lms.repository.MentorDetailRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private MentorDetailRepository mentorRepository;

	@Autowired
	private EmployeeTempRepository employeeTempRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public BatchDetails addBatch(BatchDto batchDto) {
		String methodName = "addBatch";
		BatchDetails entity = null;
		try {

			entity = new BatchDetails();
			BeanUtils.copyProperties(batchDto, entity);
			Optional<BatchDetails> findById = batchRepository.findById(entity.getId());
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

		Optional<BatchDetails> findById = batchRepository.findById(batchDto.getId());
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
			throw e;
		}
		return save;
	}

	@Override
	public List<BatchDetails> searchBatch(PagingAndFilter filter) {
		String methodName = "searchBatch";
		List<BatchDetails> search = null;
		Pageable paging = null;
		try {
			if (filter.getSortingOrder() == SortingOrder.descending)
				paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
//						,Sort.by(filter.getSortBy()).descending());
			else
				paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
//						,Sort.by(filter.getSortBy()).ascending());
			search = batchRepository.findAllByIdOrBatchNameContainingIgnoreCaseOrMentorNameContainingIgnoreCase(
					filter.getId(), filter.getParameter(), filter.getParameter(), paging);
			if (search == null) {
				throw new RuntimeException("Batch Not Found");
			}
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			throw e;
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
			throw e;
		}
		return save;
	}

	@Override
	public List<MentorDetails> searchMentor(PagingAndFilter filter) {
		String methodName = "searchMentor";
		List<MentorDetails> search = null;
		Pageable paging = null;
		try {
			if (filter.getSortingOrder() == SortingOrder.descending)
				paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
			else
				paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
			search = mentorRepository
					.findAllByEmployeeIdContainingIgnoreCaseOrMentorNameContainingIgnoreCaseOrEmailIdContainingIgnoreCase(
							filter.getParameter(), filter.getParameter(), filter.getParameter(), paging);

//			search = mentorRepository.findAllByEmployeeIdOrMentorNameOrEmailIdContainingIgnoreCase(
//					filter.getParameter(), filter.getParameter(), filter.getParameter(), paging);
			if (search == null) {
				log.error(methodName + "search returned ======>" + search);

			}
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		log.info(methodName + "returned=============>" + search);
		return search;
	}

	@Override
	public void deleteMentor(String id) {
		try {
			Optional<MentorDetails> findById = mentorRepository.findByEmployeeId(id);
			if (!findById.isPresent()) {
				throw new RuntimeException("Mentor Details Not Present On This ID");
			} else {
				mentorRepository.delete(findById.get());
//				mentorRepository.deleteByEmployeeId(id);
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

			Optional<MentorDetails> findByEmployeeIdOrEmailId = mentorRepository.findByEmployeeIdOrEmailId(mentorDto.getEmployeeId(),mentorDto.getEmailId());
			if(findByEmployeeIdOrEmailId.isPresent()) {
				throw new RuntimeException("Mentor Already Present");
			}
			
			entity = mentorRepository.save(entity);
			if (entity == null) {
				log.info(methodName, "==========> Null value received ", entity);
				throw new RuntimeException("Mentor not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}

	@Override
	public GlobalSearchDTO globalSearch(PagingAndFilter filter) {
		String methodName = "globalSearch";
		GlobalSearchDTO searchResult = new GlobalSearchDTO();
		Pageable paging = null;
		try {

			if (filter.getSortingOrder() == SortingOrder.descending)
				paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize(),
						Sort.by(filter.getSortBy()).descending());
			else
				paging = PageRequest.of(filter.getPageNumber(), filter.getPageSize());

			if (filter.getParameter().isEmpty() && filter.getId() == 0) {
				List<Employee> findAll = employeeRepo.findAll();
				if (findAll != null) {
					searchResult.setEmployees(findAll);
				}
				List<MentorDetails> findAll2 = mentorRepository.findAll();
				if (findAll2 != null) {
					searchResult.setMentors(findAll2);
				}

				return searchResult;
			}

//			List<Employee> findAllQuery = employeeRepo.findAllByEmployeeIdContainingIgnoreCaseOrBatchNameContainingIgnoreCase(filter.getParameter(),filter.getParameter(),paging);
			List<Employee> findAllQuery = employeeRepo
					.findAllByEmployeeIdContainingIgnoreCaseOrBatchNameContainingIgnoreCase(filter.getParameter(),
							filter.getParameter(), paging);
			log.info(methodName + findAllQuery.toString());
			System.out.println(findAllQuery);

			 List<MentorDetails> mentors = mentorRepository
				.findAllByEmployeeIdContainingIgnoreCaseOrMentorNameContainingIgnoreCaseOrEmailIdContainingIgnoreCase(
						filter.getParameter(), filter.getParameter(), filter.getParameter(), paging);
			if (mentors != null && mentors.size()>0)
				searchResult.setMentors(mentors);

			if (findAllQuery != null)
				searchResult.setEmployees(findAllQuery);

		} catch (Exception e) {

			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return searchResult;
	}

	@Override
	public List<EmployeeTemp> approvalRequests() {
		String methodName = "approvalRequests";
		List<EmployeeTemp> requests = null;
		try {
			requests = employeeTempRepo.findAllByStatus(ApprovalStatus.approval_pending);
			if (requests == null || requests.size() == 0) {
				log.error(methodName + "No Employee Found");
				throw new LMSCustomException("Employee Not Found");
			}
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return requests;
	}

	@Override
	public Employee approveEmployee(ApproveRejectDto approveDto) {
		String methodName = "approveEmployee";
		Employee employee = null;
		EmployeeTemp employeeEntity = null;
		try {
			Optional<EmployeeTemp> findByEmployeeId = employeeTempRepo.findByEmployeeId(approveDto.getEmployeeId());
			log.info(methodName +"findByEmployeeId=temp=="+findByEmployeeId.get() );
			if (findByEmployeeId.isEmpty()) {
				log.error(methodName + "findByEmployeeId returned Null");
				throw new LMSCustomException("Employee Not Found");
			}
			employeeEntity = findByEmployeeId.get();

			Optional<BatchDetails> batchOp = batchRepository.findByIdAndBatchName(approveDto.getBatchId(),
					approveDto.getBatchName());
			if (batchOp.isEmpty()) {
				log.error(methodName + "batchOp returned Null");
				throw new LMSCustomException("Batch Not Found");
			}
			BatchDetails batchDetails = batchOp.get();
			employee = new Employee();
			BeanUtils.copyProperties(employeeEntity, employee);
			
			log.info(methodName+"employeeEntity===>" +employeeEntity);
			log.info(methodName+"employee      ===>" +employee);

			employee.setBatchId(approveDto.getBatchId());
			employee.setBatchName(approveDto.getBatchName());
			employee.setBatchDetails(batchDetails);
			employee = employeeRepo.save(employee);
			if (employee != null && employee.getEmployeeId() != null) {

				employeeTempRepo.delete(employeeEntity);
			}

		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			throw e;

		}
		return employee;
	}

	@Override
	public EmployeeTemp rejectEmployee(ApproveRejectDto rejectDto) {
		String methodName = "rejectEmployee";
		EmployeeTemp entity = null;
		try {

			entity = new EmployeeTemp();

			Optional<EmployeeTemp> findByEmployeeId = employeeTempRepo.findByEmployeeId(rejectDto.getEmployeeId());
			if (findByEmployeeId.isEmpty()) {
				log.info(methodName, "findByEmployeeId ==========> Null value received ", entity);
				throw new LMSCustomException("Employee not found");

			}
			entity = findByEmployeeId.get();
			entity.setStatus(ApprovalStatus.rejected);
			entity = employeeTempRepo.save(entity);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
			throw e;
		}
		return entity;
	}

	@Override
	public Employee getEmployee( String employeeId) {
		String methodName = "getEmployee";
		Employee search = null;
		try {
			 Optional<Employee> findByEmployeeId = employeeRepo.findByEmployeeId(employeeId);
			if (findByEmployeeId.isEmpty()) {
				throw new RuntimeException("Employee Not Found");
			}
			search=findByEmployeeId.get();
		} catch (Exception e) {
			log.error(methodName + "==========>" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return search;
	}

}
