package com.tyss.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MockRatingDto {
	private int mockNo;
	private long batchId;
	private String employeeId;
	private String mockType;
	private String mockTakenBy;
	private String technology;
	private MockRatingEnum practicalKnowledge;
	private MockRatingEnum theoreticalKnowledge;
	private MockRatingEnum overallFeedback;
	private String detailedFeedback;
	

}
