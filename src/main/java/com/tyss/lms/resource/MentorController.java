package com.tyss.lms.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.AdminConstant;
import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;
import com.tyss.lms.service.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mentor")
@RequiredArgsConstructor
public class MentorController {

	private MentorService mockService;

	@PostMapping("/mock/add")
	public ResponseEntity<ResponseMessage> addBatch(@RequestBody MockDetailDto mockDto) {

		MockDetails addMock = mockService.addMock(mockDto);
		if (addMock != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, addMock);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, addMock);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/batch/view")
	public ResponseEntity<ResponseMessage> viewBatch() {

		List<BatchDetails> addBatch = mockService.viewBatch();
		if (addBatch != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/mock/rate")
	public ResponseEntity<ResponseMessage> rateMock(@RequestBody MockRatingDto mockRatingDto) {

		MockRatings mockRating = mockService.rateMock(mockRatingDto);
		if (mockRating != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, mockRating);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, mockRating);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

}
