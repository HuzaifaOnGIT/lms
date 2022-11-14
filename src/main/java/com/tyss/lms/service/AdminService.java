package com.tyss.lms.service;

import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.EmployeeEntity;

public interface AdminService {

	BatchDetails addBatch(BatchDto batchDto);

	BatchDetails updateBatch(BatchDto batchDto);

	void deleteBatch(Long id);

	BatchDetails searchBatch(Long id, String batchName);

}
