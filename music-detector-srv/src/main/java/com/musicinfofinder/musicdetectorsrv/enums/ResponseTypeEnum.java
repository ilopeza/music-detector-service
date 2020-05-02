package com.musicinfofinder.musicdetectorsrv.enums;

public enum ResponseTypeEnum {
	CODE(0, "code");

	private int code;
	private String name;

	ResponseTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
