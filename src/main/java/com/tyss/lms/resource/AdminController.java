package com.tyss.lms.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.AdminConstant;
import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.GlobalSearchDTO;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.MentorDetails;
import com.tyss.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private AdminService adminService;

	@PostMapping("/batch/add")
	public ResponseEntity<ResponseMessage> addBatch(@RequestBody BatchDto batchDto) {

		BatchDetails addBatch = adminService.addBatch(batchDto);
		if (addBatch != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/batch/update")
	public ResponseEntity<ResponseMessage> updateBatch(@RequestBody BatchDto batchDto) {

		BatchDetails updateBatch = adminService.updateBatch(batchDto);
		if (updateBatch != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.UPDATE_SUCCESS,
					updateBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.UPDATE_FAIL, updateBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/batch/delete")
	public ResponseEntity<ResponseMessage> deleteBatch(@PathVariable Long id) {

		adminService.deleteBatch(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, AdminConstant.DELETE_SUCCESS, null),
				HttpStatus.OK);
	}

	@GetMapping("/batch/search/{batchId},{batchName}")
	public ResponseEntity<ResponseMessage> searchBatch(@PathVariable Long batchId, @PathVariable String batchName) {

		BatchDetails searchResult = adminService.searchBatch(batchId, batchName);
		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/mentor/add")
	public ResponseEntity<ResponseMessage> addMentor(@RequestBody MentorDto mentorDto) {

		MentorDetails addMentor = adminService.addMentor(mentorDto);
		if (addMentor != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.ADD_SUCCESS, addMentor);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.ADD_FAIL, addMentor);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping ("/mentor/update")
	public ResponseEntity<ResponseMessage> updateMentor(@RequestBody MentorDto mentorDto) {

		MentorDetails updateMentor = adminService.updateMentor(mentorDto);
		if (updateMentor != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.UPDATE_SUCCESS,
					updateMentor);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.UPDATE_FAIL, updateMentor);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/mentor/delete/{id}")
	public ResponseEntity<ResponseMessage> deleteMentor(@PathVariable String id) {

		adminService.deleteMentor(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, AdminConstant.DELETE_SUCCESS, null),
				HttpStatus.OK);
	}

	@GetMapping("/mentor/search/{employeeId},{mentorName}")
	public ResponseEntity<ResponseMessage> searchMentor(@PathVariable String employeeId, @PathVariable String mentorName) {

		MentorDetails searchResult=null;
		try {
			searchResult = adminService.searchMentor(employeeId, mentorName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/search/{parameter}")
	public ResponseEntity<ResponseMessage> globalSearch(@PathVariable String parameter) {

		GlobalSearchDTO searchResult=null;
		try {
			 searchResult= adminService.globalSearch(parameter);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
}
