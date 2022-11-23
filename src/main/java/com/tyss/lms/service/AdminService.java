package com.tyss.lms.service;

import java.util.List;

import javax.validation.Valid;

import com.tyss.lms.dto.ApproveRejectDto;
import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.GlobalSearchDTO;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.dto.PagingAndFilter;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.entity.MentorDetails;

public interface AdminService {

	public BatchDetails addBatch(BatchDto batchDto);

	public BatchDetails updateBatch(BatchDto batchDto);

	public void deleteBatch(Long id);

	public List<BatchDetails> searchBatch(PagingAndFilter filter);

	public MentorDetails updateMentor(MentorDto mentorDto);

//	public List<MentorDetails> searchMentor(String employeeId, String mentorName);

	public void deleteMentor(String id);

	public MentorDetails addMentor(MentorDto mentorDto);

	public GlobalSearchDTO globalSearch(PagingAndFilter filter);

	public List<EmployeeTemp> approvalRequests();

	public Employee approveEmployee(ApproveRejectDto approveDto);

	public EmployeeTemp rejectEmployee(ApproveRejectDto rejectDto);

	public List<MentorDetails> searchMentor(PagingAndFilter filter);

	public Employee getEmployee(@Valid String employeeId);

}
