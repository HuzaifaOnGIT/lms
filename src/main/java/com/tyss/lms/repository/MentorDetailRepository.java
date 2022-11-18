package com.tyss.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.MentorDetails;

@Repository
public interface MentorDetailRepository extends JpaRepository<MentorDetails, Long> {

	public Optional<MentorDetails> findByEmployeeId(String employeeId);

	public void deleteByEmployeeId(String id);

	public List<MentorDetails> findAllByEmployeeIdOrMentorNameOrEmailIdContainingIgnoreCase(String parameter, String string, String string2, Pageable filter);
//	public List<MentorDetails> findAllByEmployeeIdOrMentorNameOrEmailIdOrSkillsContainingIgnoreCase(String parameter,String parameter2,String parameter3, Pageable filter);




}
