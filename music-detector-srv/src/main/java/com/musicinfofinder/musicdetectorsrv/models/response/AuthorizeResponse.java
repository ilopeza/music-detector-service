package com.musicinfofinder.musicdetectorsrv.models.response;

public class AuthorizeResponse {
	private String code;
	private String state;

	public AuthorizeResponse() {
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
}
