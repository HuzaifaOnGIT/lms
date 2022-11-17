package com.tyss.lms.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MockDetails;
@Repository
public interface MockRepository extends PagingAndSortingRepository<MockDetails, Long>{

}
