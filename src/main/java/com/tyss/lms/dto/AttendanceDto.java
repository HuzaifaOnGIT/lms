package com.tyss.lms.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceDto {
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date date;
	@NotNull(message = "batch id cant be null")
	private long batchId;
	@NotNull
	@NotNull(message = "employeeId id cant be null")
	private String employeeId;
	@NotNull
	private AttendanceEnum attendance;

}
