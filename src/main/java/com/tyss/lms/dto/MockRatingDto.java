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
	private Long employeeId;
	private String mockType;
	private String mockTakenBy;
	private String technology;
	private String practicalKnowledge;
	private String theoreticalKnowledge;
	private String overallFeedback;
	private String detailedFeedback;
	

}
