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

	@NotBlank
	@NotNull
	private int mockNo;
	@NotBlank
	@NotNull
	private Integer batchId;
	@NotBlank
	@NotNull
	private String batchName;
	@NotBlank
	@NotNull
	private String mentorName;
	@NotNull
	private List<String> technologies;
	@NotBlank
	@NotNull
	private String startDate;
	@NotBlank
	@NotNull
	private String endDate;
	@NotBlank
	@NotNull
	private Status status;
}
