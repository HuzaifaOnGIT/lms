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
	@NotNull
	private int mockNo;
	@NotNull
	private long batchId;
	@NotNull
	private String employeeId;
	@NotNull
	private String mockType;
	@NotNull
	private String mockTakenBy;
	@NotNull
	private String technology;
	@NotNull
	private MockRatingEnum practicalKnowledge;
	@NotNull
	private MockRatingEnum theoreticalKnowledge;
	@NotNull
	private MockRatingEnum overallFeedback;
	@NotNull
	private String detailedFeedback;
	

}
