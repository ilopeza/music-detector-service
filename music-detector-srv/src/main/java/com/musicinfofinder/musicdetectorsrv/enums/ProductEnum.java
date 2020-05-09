package com.musicinfofinder.musicdetectorsrv.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductEnum {
	@JsonProperty("free")
	FREE("free"),
	@JsonProperty("premium")
	PREMIUM("premium");

	String productName;

	ProductEnum(String productName) {
		this.productName = productName;
	}
}
