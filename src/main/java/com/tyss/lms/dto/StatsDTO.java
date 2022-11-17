package com.tyss.lms.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsDTO {
	private Map<Gender, Float> genderDetail;
	private Map<Integer, Integer> yopDetail;
	private Map<Integer, Integer> experienceDetail;
	private Map<Integer, Integer> educationDetail;
	private Map<Integer, Integer> performanceDetail;
	
	

}
