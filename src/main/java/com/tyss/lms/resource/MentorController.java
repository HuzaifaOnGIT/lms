package com.tyss.lms.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.AdminConstant;
import com.tyss.lms.dto.EmployeeStatus;
import com.tyss.lms.dto.MockDetailDto;
import com.tyss.lms.dto.MockRatingDto;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.dto.StatsDTO;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.MockDetails;
import com.tyss.lms.entity.MockRatings;
import com.tyss.lms.service.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mentor")
/**
 *-	Gender (Pie-chart)
-	Year of Passing (Column Chart)
-	Experience (Column Chart)
-	Degree (Bar Graph)
-	Batch Performance(Pie-chart)

 */
@RequiredArgsConstructor
public class MentorController {

	@Autowired
	private MentorService mentorService;

	@PostMapping("/mock/add")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> addBatch(@RequestBody MockDetailDto mockDto) {

		MockDetails addMock = mentorService.addMock(mockDto);
		if (addMock != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, addMock);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, addMock);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/batch/view")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> viewBatch() {

		List<BatchDetails> addBatch = mentorService.viewBatch();
		if (addBatch != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/mock/rate")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> rateMock(@RequestBody MockRatingDto mockRatingDto) {

		MockRatings mockRating = mentorService.rateMock(mockRatingDto);
		if (mockRating != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, mockRating);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, mockRating);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/employee/changestatus/{employeeId},{status}")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> changeStatus(@PathVariable String employeeId, @PathVariable EmployeeStatus status) {

		  Employee changeStatus= mentorService.changeStatus( employeeId, status);
		if (changeStatus != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, changeStatus);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, changeStatus);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/employee/search/{employeeId}")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> searchEmployee(@PathVariable String employeeId) {

		Employee employee = mentorService.searchEmployee(employeeId);
		if (employee != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS, employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.SEARCH_FAIL, employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/dashboard/gender")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> genderStats(@PathVariable long batchId) {

		StatsDTO result = mentorService.genderStats(batchId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, new StatsDTO().builder().genderDetail(result.getGenderDetail()).build());
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/dashboard/yop")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> yopStats(@PathVariable long batchId) {

		StatsDTO result = mentorService.yopStats(batchId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, new StatsDTO().builder().yopDetail(result.getYopDetail()).build());
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/dashboard/degree")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> degreeStats(@PathVariable long batchId) {

		StatsDTO result = mentorService.degreeStats(batchId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, new StatsDTO().builder().yopDetail(result.getYopDetail()).build());
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/dashboard/experience")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> experienceStats(@PathVariable long batchId) {

		StatsDTO result = mentorService.experienceStats(batchId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, new StatsDTO().builder().yopDetail(result.getYopDetail()).build());
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/dashboard/performance")
	@PreAuthorize("hasRole('ROLE_MENTOR')")
	public ResponseEntity<ResponseMessage> batchPerformance(@PathVariable long batchId) {

		StatsDTO result = mentorService.batchPerformance(batchId);
		if (result != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, new StatsDTO().builder().yopDetail(result.getYopDetail()).build());
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, result);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

}
