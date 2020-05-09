package com.musicinfofinder.musicdetectorsrv.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum  ObjectType {
	@JsonProperty("user")
	USER(0, "user"),
	@JsonProperty("track")
	TRACK(1, "track"),
	@JsonProperty("episode")
	EPISODE(1, "episode");

	int code;
	String objectTypeName;

	ObjectType(int code, String objectTypeName) {
		this.code = code;
		this.objectTypeName = objectTypeName;
	}
}
