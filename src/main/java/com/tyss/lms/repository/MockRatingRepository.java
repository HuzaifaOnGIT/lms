package com.tyss.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MockRatings;
@Repository
public interface MockRatingRepository extends  PagingAndSortingRepository<MockRatings, Long>{

	Optional<List<MockRatings>> findAllByBatchId(long batchId);


	Optional<List<MockRatings>> findAllByEmployeeIdAndMockNo(String employeeId, int mockNo);


	Optional<List<MockRatings>> findAllByEmployeeId(String empId);

}
