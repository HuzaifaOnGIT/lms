package com.tyss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;
@Repository
public interface MockRatingRepository extends  JpaRepository<MockRatings, Long>{

}
