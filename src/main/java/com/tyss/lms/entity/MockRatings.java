package com.tyss.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tyss.lms.dto.MockRatingEnum;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mockRatingsId;
	private int mockNo;
	private long batchId;
	private String employeeId;
	private String mockType;
	private String mockTakenBy;
	private String technology;
	private MockRatingEnum practicalKnowledge;
	private MockRatingEnum theoreticalKnowledge;
	private String overallFeedback;
	private String detailedFeedback;
	

}
