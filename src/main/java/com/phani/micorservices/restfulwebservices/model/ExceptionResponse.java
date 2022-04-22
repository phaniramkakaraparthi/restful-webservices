package com.phani.micorservices.restfulwebservices.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIgnoreProperties(value = {"message","details"})
public class ExceptionResponse {
	
	private Date timeStamp;
	private String message;
	//@JsonIgnore
	private String details;
	
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
