package com.tyss.lms.service;

import java.text.ParseException;
import java.util.List;

import com.tyss.lms.dto.AttendanceDto;
import com.tyss.lms.dto.EmployeeStatus;
import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.dto.PagingAndFilter;
import com.tyss.lms.dto.StatsDTO;
import com.tyss.lms.entity.AttendanceEntity;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;

public interface MentorService {

	public MockDetails addMock(MockDetailDto mockDto) ;

	public List<BatchDetails> viewBatch();

	public MockRatings rateMock(MockRatingDto mockRatingDto);

	public 	Employee changeStatus(String employeeId, EmployeeStatus status);

	public List<Employee> searchEmployee(PagingAndFilter employeeId);

	public StatsDTO genderStats(long batchId);

	public StatsDTO yopStats(long batchId);

	public StatsDTO batchPerformance(long batchId);

	public StatsDTO experienceStats(long batchId);

	public StatsDTO degreeStats(long batchId);

	public AttendanceEntity addAttendance(AttendanceDto attendanceDto) ;

	public List<AttendanceEntity> getAttendance(String empId);

}
