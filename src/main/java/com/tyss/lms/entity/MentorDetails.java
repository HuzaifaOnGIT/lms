package com.tyss.lms.entity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "mentor_details")
public class MentorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mentorDetailsId;
	private String mentorName;
	private String emailId;
	private String EmployeeId;
	@Convert(converter = ListToStringUtil.class)
	private List<String> skills;

}
