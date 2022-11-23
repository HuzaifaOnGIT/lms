package com.tyss.lms.dto;

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
public class MockRatingDto {
	@NotBlank
	@NotNull
	private int mockNo;
	@NotBlank
	@NotNull
	private long batchId;
	@NotBlank
	@NotNull
	private String employeeId;
	@NotBlank
	@NotNull
	private String mockType;
	@NotBlank
	@NotNull
	private String mockTakenBy;
	@NotBlank
	@NotNull
	private String technology;
	@NotBlank
	@NotNull
	private MockRatingEnum practicalKnowledge;
	@NotBlank
	@NotNull
	private MockRatingEnum theoreticalKnowledge;
	@NotBlank
	@NotNull
	private MockRatingEnum overallFeedback;
	@NotBlank
	@NotNull
	private String detailedFeedback;
	

}
