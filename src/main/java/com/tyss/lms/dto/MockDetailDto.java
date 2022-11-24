package com.tyss.lms.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MockDetailDto {


	@NotNull
	private int mockNo;

	private Integer batchId;

	private String batchName;

	private String mentorName;
	@NotNull
	private List<String> technologies;
	@NotBlank
	@NotNull
	private String startDate;
	@NotBlank
	@NotNull
	private String endDate;
	@NotNull
	private Status status;
}
