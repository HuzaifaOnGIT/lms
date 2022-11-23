package com.tyss.lms.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BatchDto {

	@NotNull
	@NotBlank
	private Long Id;
	private String batchName;
	private String mentorName;
	private List<String> technologies;
	private String startDate;
	private String endDate;
	private Status status;
}
