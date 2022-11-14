package com.tyss.lms.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.AdminConstant;
import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.entity.BatchDetails;
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
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.BATCH_ADD_SUCCESS, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.BATCH_ADD_FAIL, addBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/batch/update")
	public ResponseEntity<ResponseMessage> updateBatch(@RequestBody BatchDto batchDto) {

		BatchDetails updateBatch = adminService.updateBatch(batchDto);
		if (updateBatch != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.BATCH_UPDATE_SUCCESS,
					updateBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.BATCH_UPDATE_FAIL, updateBatch);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_MODIFIED);
		}
	}

	@PostMapping("/batch/delete")
	public ResponseEntity<ResponseMessage> deleteBatch(@PathVariable Long id) {

		adminService.deleteBatch(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, AdminConstant.BATCH_DELETE_SUCCESS, null),
				HttpStatus.OK);
	}

	@PostMapping("/batch/search/{batchId},{batchName}")
	public ResponseEntity<ResponseMessage> searchBatch(@PathVariable Long batchId, @PathVariable String batchName) {

		BatchDetails searchResult = adminService.searchBatch(batchId, batchName);
		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.BATCH_SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.BATCH_SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
}
