package com.social.credittest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse<T> {
	
	@JsonProperty("code")
	private int code;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("data")
	private T data;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
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
