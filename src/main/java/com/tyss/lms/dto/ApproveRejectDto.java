package com.tyss.lms.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApproveRejectDto {

	@NotNull
	private String employeeId;
	@NotNull
	private String batchName;
	private long batchId;
}
