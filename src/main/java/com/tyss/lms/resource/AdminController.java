package com.tyss.lms.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.lms.constants.AdminConstant;
import com.tyss.lms.dto.ApproveRejectDto;
import com.tyss.lms.dto.BatchDto;
import com.tyss.lms.dto.GlobalSearchDTO;
import com.tyss.lms.dto.MentorDto;
import com.tyss.lms.dto.PagingAndFilter;
import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.entity.BatchDetails;
import com.tyss.lms.entity.Employee;
import com.tyss.lms.entity.EmployeeTemp;
import com.tyss.lms.entity.MentorDetails;
import com.tyss.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/batch/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> addBatch(@Valid @RequestBody BatchDto batchDto) {

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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> updateBatch(@Valid  @RequestBody BatchDto batchDto) {

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

	@DeleteMapping("/batch/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> deleteBatch(@PathVariable Long id) {

		adminService.deleteBatch(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, AdminConstant.DELETE_SUCCESS, null),
				HttpStatus.OK);
	}

	@PostMapping("/batch/search")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> searchBatch(@Valid  PagingAndFilter filter) {

	 List<BatchDetails> searchResult = adminService.searchBatch(filter);
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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> addMentor(@Valid  @RequestBody MentorDto mentorDto) {

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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> updateMentor(@Valid  @RequestBody MentorDto mentorDto) {

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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> deleteMentor(@Valid  @PathVariable String id) {

		adminService.deleteMentor(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, AdminConstant.DELETE_SUCCESS, null),
				HttpStatus.OK);
	}

	@PostMapping("/mentor/search")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseMessage> searchMentor(@Valid  @RequestBody PagingAndFilter filter) {

		List<MentorDetails> searchResult=null;
		
			searchResult = adminService.searchMentor(filter);

		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true , AdminConstant.SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/search")
//	@PostMapping("/search/{parameter}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> globalSearch(@Valid  @RequestBody PagingAndFilter filter) {

		GlobalSearchDTO searchResult= adminService.globalSearch(filter);
		
		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/employee/approval-requests")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> approvalRequests() {

		List<EmployeeTemp> requests = adminService.approvalRequests();
		if (requests != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.FETCH_SUCCESS, requests);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.SEARCH_FAIL, requests);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/employee/approve")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> approveEmployee( @RequestBody ApproveRejectDto approveDto) {

		Employee employee = adminService.approveEmployee(approveDto);
		if (employee != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.APPROVE_SUCCESS, employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.APPROVE_FAIL, employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/employee/reject")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> rejectEmployee(@Valid  @RequestBody ApproveRejectDto rejectDto) {

		EmployeeTemp employee = adminService.rejectEmployee(rejectDto);
		if (employee != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.REJECT_SUCCESS, employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, AdminConstant.REJECT_FAIL, employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/employee/get/{employeeId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseMessage> getEmployee(@Valid  @PathVariable String employeeId) {

		Employee searchResult=null;
		
		searchResult = adminService.getEmployee(employeeId);

		if (searchResult != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, AdminConstant.SEARCH_SUCCESS,
					searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true , AdminConstant.SEARCH_FAIL, searchResult);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
}
