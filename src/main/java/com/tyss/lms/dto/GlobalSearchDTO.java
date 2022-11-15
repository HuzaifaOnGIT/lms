package com.tyss.lms.dto;

import java.util.ArrayList;
import java.util.List;

import com.tyss.lms.entity.EmployeeEntity;
import com.tyss.lms.entity.MentorDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalSearchDTO {
	
	List<EmployeeEntity> employees=new ArrayList<>();;
	List<MentorDetails> mentors=new ArrayList<>();

}
