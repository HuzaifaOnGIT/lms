package com.tyss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MockDetails;
@Repository
public interface MockRepository extends JpaRepository<MockDetails, Long>{

}
