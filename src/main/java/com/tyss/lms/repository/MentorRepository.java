package com.tyss.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MentorDetails;

@Repository
public interface MentorRepository extends JpaRepository<MentorDetails, Long> {

	public Optional<MentorDetails> findByEmployeeId(String employeeId);

	public void deleteByEmployeeId(String id);

}
