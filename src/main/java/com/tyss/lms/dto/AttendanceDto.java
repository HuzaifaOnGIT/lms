package com.tyss.lms.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceDto {
	
	@NotBlank
	@NotNull
	private Date date;
	@NotBlank
	@NotNull
	private long batchId;
	@NotBlank
	@NotNull
	private String employeeId;
	@NotBlank
	@NotNull
	private AttendanceEnum attendance;

}
