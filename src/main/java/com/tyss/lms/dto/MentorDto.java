package com.tyss.lms.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MentorDto {

	@NotNull
	@Size(min = 3)
	private String mentorName;
	@Email
	private String emailId;
	@NotNull
	@Size(min = 5)
	private String EmployeeId;
	private List<String> skills;

}
