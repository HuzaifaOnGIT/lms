package com.tyss.lms.customexception;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.lms.dto.ResponseMessage;
import com.tyss.lms.dto.ValidationResponse;

@RestControllerAdvice
public class LMSExceptionHandler extends ResponseEntityExceptionHandler {


	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 List<String> list = ex.getBindingResult().getAllErrors().stream().map(t -> t.getDefaultMessage()).toList();
       return new ResponseEntity<Object>(new ValidationResponse(true, list, null), HttpStatus.BAD_REQUEST);
//		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

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
	
//	
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationResponse> handleInvalidArgument(MethodArgumentNotValidException ex) {
//        List<String> list = ex.getBindingResult().getAllErrors().stream().map(t -> t.getDefaultMessage()).toList();
//        return new ResponseEntity<ValidationResponse>(new ValidationResponse(true, list, null), HttpStatus.BAD_REQUEST);
//    }
  

}
