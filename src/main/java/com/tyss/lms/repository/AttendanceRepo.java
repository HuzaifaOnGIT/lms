package com.tyss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.lms.entity.AttendanceEntity;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {

	List<AttendanceEntity> findByEmployeeId(String empId);

}
