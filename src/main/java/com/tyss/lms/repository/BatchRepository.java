package com.tyss.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.BatchDetails;

@Repository
public interface BatchRepository extends JpaRepository<BatchDetails, Long> {

	public Optional<BatchDetails> findByBatchId(Long batchId);

	public BatchDetails findByBatchIdOrBatchName(Long batchId, String batchName);

//	public Optional<BatchDetails> findByEmployeeId(String batchId);
//
//	public MentorDetails findByEmployeeIdOrMentorName(String employeeId, String mentorName);

}
