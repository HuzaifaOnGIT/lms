package com.tyss.lms.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.lms.dto.ResponseMessage;

@RestControllerAdvice
public class LMSExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(LMSCustomException.class)
	public ResponseEntity<ResponseMessage> exceptionhandler(LMSCustomException exception) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseMessage> exceptionhandler(RuntimeException exception) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> exceptionhandler(Exception exception,WebRequest request) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, exception.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
