package com.tyss.lms.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PagingAndFilter {
	private long id;
	private String parameter;
	private int pageNumber;
	@NotNull
	private int pageSize;
	private String sortBy;
	private SortingOrder SortingOrder;
	 

}
