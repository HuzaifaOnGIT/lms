package com.tyss.lms.dto;

import java.util.List;
import java.util.Map;

import com.tyss.lms.entity.MockRatings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsDTO {
	private List< Map<Gender, Float> >genderDetail;
	private Map<Integer, Integer> yopDetail;
	private Map<Long,Integer> experienceDetail;
	private Map<String,Integer> degreeStats;
	private Map<String,Integer> performance;
//	List<Map<String,Integer> > performance;
//	private  Map<String, List<MockRatings>> performance;
	
	

}
