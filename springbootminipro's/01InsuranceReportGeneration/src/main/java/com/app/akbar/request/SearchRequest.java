package com.app.akbar.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	
	private String gender;
	private String planName;
	private String planStatus;
	private String planStartDate;
	private String planEndDate;

}
