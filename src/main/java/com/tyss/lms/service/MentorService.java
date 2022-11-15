package com.tyss.lms.service;

import java.util.List;

import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;

public interface MentorService {

	public MockDetails addMock(MockDetailDto mockDto) ;

	public List<BatchDetails> viewBatch();

	public MockRatings rateMock(MockRatingDto mockRatingDto);

}
