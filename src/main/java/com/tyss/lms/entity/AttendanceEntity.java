package com.tyss.lms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tyss.lms.dto.AttendanceEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Attendance_Details")
public class AttendanceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long attendanceId;
	private String date;
	private long batchId;
	private String employeeId;
	private AttendanceEnum attendance;
	

}
