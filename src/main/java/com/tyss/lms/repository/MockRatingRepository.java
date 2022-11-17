package com.tyss.lms.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MockRatings;
@Repository
public interface MockRatingRepository extends  PagingAndSortingRepository<MockRatings, Long>{

}
