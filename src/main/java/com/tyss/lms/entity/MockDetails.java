package com.tyss.lms.entity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "mock_details")
public class MockDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mockDetailsId;
	private Integer batchId;
	private String batchName;
	private String mentorName;
	@Convert(converter = ListToStringUtil.class)
	private List<String> technologies;
	private String startDate;
	private String endDate;
	private Status status;
}
