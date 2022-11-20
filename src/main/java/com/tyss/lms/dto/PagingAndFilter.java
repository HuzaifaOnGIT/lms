package com.tyss.lms.dto;

import lombok.Data;

@Data
public class PagingAndFilter {
	private long id;
	private String parameter;
	private int pageNumber;
	private int pageSize;
	private String sortBy;
	private SortingOrder SortingOrder;
	 

}
