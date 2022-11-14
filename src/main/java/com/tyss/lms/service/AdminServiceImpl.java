package com.tyss.lms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.repository.BatchRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

	private BatchRepository batchRepository;

	@Override
	public BatchDetails addBatch(BatchDto batchDto) {
		String methodName = "addBatch";
		BatchDetails entity = null;
		BatchDetails save = null;
		try {

			entity = new BatchDetails();
			BeanUtils.copyProperties(batchDto, entity);
			Optional<BatchDetails> findById = batchRepository.findById(entity.getBatchId());
			int resumeNo = 1;
			if (findById.isPresent()) {

				log.error(methodName, "Batch already exists with this Id", entity);
				throw new RuntimeException("Batch already exists with this Id");
			}

			save = batchRepository.save(entity);
			if (save == null) {
				log.info(methodName, " Null value received ", entity);
				throw new RuntimeException("Batch not saved");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(methodName + e.getMessage());
		}
		return save;
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

}
