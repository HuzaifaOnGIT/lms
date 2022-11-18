package com.tyss.lms.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.lms.dto.ResponseMessage;

@RestControllerAdvice
public class LMSExceptionHandler {

	@ExceptionHandler(LMSCustomException.class)
    public ResponseEntity<ResponseMessage> customExceptionHandler(LMSCustomException exception) {
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationResponce> handleInvalidArgument(MethodArgumentNotValidException ex) {
//        List<String> list = ex.getBindingResult().getAllErrors().stream().map(t -> t.getDefaultMessage()).toList();
//        return new ResponseEntity<ValidationResponce>(new ValidationResponce(true, list, null), HttpStatus.BAD_REQUEST);
//    }
}
