package com.tyss.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.BatchDetails;

@Repository
public interface BatchRepository extends JpaRepository<BatchDetails, Long> {


	public BatchDetails findByIdOrBatchName(Long batchId, String batchName);
	
	public Optional<BatchDetails> findById(Long batchId);



	public List<BatchDetails> findAllByIdOrBatchNameContainingIgnoreCaseOrMentorNameContainingIgnoreCase(long id,
			String parameter, String parameter2, Pageable paging);

	public Optional<BatchDetails> findByIdAndBatchName(long batchId, String batchName);



}
