package com.musicinfofinder.musicdetectorsrv.models.response.dto;

import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isEmpty;

public class AuthorizationDTO {
	private String code;
	private String state;
	private String error;

	public AuthorizationDTO() {
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean hasError() {
		return !isEmpty(error) && isEmpty(code);
	}

	public boolean isValidState(String state) {
		return equalsIgnoreCase(this.state, state);
	}
}
