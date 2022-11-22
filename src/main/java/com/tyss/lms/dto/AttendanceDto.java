package com.tyss.lms.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceDto {
	
	private Date date;
	private long batchId;
	private String employeeId;
	private AttendanceEnum attendance;

}
