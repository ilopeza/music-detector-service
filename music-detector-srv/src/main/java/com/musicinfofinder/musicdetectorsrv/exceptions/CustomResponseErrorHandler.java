package com.musicinfofinder.musicdetectorsrv.exceptions;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		final HttpStatus status = response.getStatusCode();
		return status.is4xxClientError() || status.is5xxServerError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		final HttpStatus statusCode = response.getStatusCode();
		final InputStream body = response.getBody();
		String message = "";
		JSONParser parser = new JSONParser(body);
		try {
			final LinkedHashMap<String, Object> stringObjectLinkedHashMap = parser.parseObject();
			message = stringObjectLinkedHashMap.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new SpotifyWebAPIException(statusCode, message);
	}
}
