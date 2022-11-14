package com.tyss.lms.entity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tyss.lms.dto.Status;
import com.tyss.lms.util.ListToStringUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "batch_details")
public class BatchDetails {

	@Id
	private Long batchId;
	private String batchName;
	private String mentorName;
	@Convert(converter = ListToStringUtil.class)
	private List<String> technologies;
	private String startDate;
	private String endDate;
	private Status status;
}
