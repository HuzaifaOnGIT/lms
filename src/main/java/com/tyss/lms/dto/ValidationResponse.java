package com.tyss.lms.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResponse {
	
	public boolean error;
	public List<String> message;
	public Object data;
	public ValidationResponse(boolean error, List<String> message, Object data) {
		this.error = error;
		this.message = message;
		this.data = data;
	}
	public ValidationResponse() {
	} 
	
}