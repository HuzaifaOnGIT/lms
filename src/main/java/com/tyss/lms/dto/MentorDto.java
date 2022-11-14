package com.tyss.lms.dto;

import java.util.List;

import lombok.Data;

@Data
public class MentorDto {

	private String mentorName;
	private String emailId;
	private String EmployeeId;
	private List<String> skills;

}
