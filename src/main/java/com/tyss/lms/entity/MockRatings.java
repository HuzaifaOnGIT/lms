package com.tyss.lms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mock_rating")
public class MockRatings {
	@Id
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
