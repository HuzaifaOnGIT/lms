package com.tyss.lms.dto;

import java.util.List;

import lombok.Data;

@Data
public class BatchDto {

	private Long batchId;
	private String batchName;
	private String mentorName;
//	@Convert(converter = ListToStringUtil.class)
	private List<String> technologies;
	private String startDate;
	private String endDate;
	private Status status;
}
