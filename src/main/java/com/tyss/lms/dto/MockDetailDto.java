package com.tyss.lms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MockDetailDto {

	private int mockNo;
	private Integer batchId;
	private String batchName;
	private String mentorName;
	private List<String> technologies;
	private String startDate;
	private String endDate;
	private Status status;
}
