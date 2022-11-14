package com.tyss.lms.service;

import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.MentorDetails;

public interface AdminService {

	public BatchDetails addBatch(BatchDto batchDto);

	public BatchDetails updateBatch(BatchDto batchDto);

	public void deleteBatch(Long id);

	public BatchDetails searchBatch(Long id, String batchName);

	public MentorDetails updateMentor(MentorDto mentorDto);

	public MentorDetails searchMentor(String employeeId, String mentorName);

	public void deleteMentor(String id);

	public MentorDetails addMentor(MentorDto mentorDto);

}
