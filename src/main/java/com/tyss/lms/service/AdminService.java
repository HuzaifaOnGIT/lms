package com.tyss.lms.service;

import java.util.List;

import com.tyss.lms.dto.ApproveRejectDto;
import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.GlobalSearchDTO;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.dto.PagingAndFilter;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeEntity;
import com.tyss.lms.entity.MentorDetails;

public interface AdminService {

	public BatchDetails addBatch(BatchDto batchDto);

	public BatchDetails updateBatch(BatchDto batchDto);

	public void deleteBatch(Long id);

	public BatchDetails searchBatch(Long id, String batchName);

	public MentorDetails updateMentor(MentorDto mentorDto);

	public List<MentorDetails> searchMentor(String employeeId, String mentorName);

	public void deleteMentor(String id);

	public MentorDetails addMentor(MentorDto mentorDto);

	public GlobalSearchDTO globalSearch(String parameter,PagingAndFilter filter);

	public List<EmployeeEntity> approvalRequests();

	public Employee approveEmployee(ApproveRejectDto approveDto);

	public EmployeeEntity rejectEmployee(ApproveRejectDto rejectDto);

}
