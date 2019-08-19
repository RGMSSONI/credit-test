package com.social.credittest.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class GenericResponse {
	
	@JsonProperty("code")
	private int code;
	
	@JsonProperty("status")
	private String status;
	
	public int getErrorCode() {
		return code;
	}
	public void setErrorCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
