package com.musicinfofinder.musicdetectorsrv.models.response;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.GeneralMusicDetectorException;
import com.musicinfofinder.musicdetectorsrv.models.response.error.AuthenticationErrorResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.error.RegularErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomErrorResponseHandlerTest {
	private static final String HTTP_STATUS_CODE_EXCEPTION_RESPONSE_BODY = "{\n" +
					"  \"error\": {\n" +
					"    \"status\": 401,\n" +
					"    \"message\": \"The access token expired\"\n" +
					"  }\n" +
					"}";
	private static final String HTTP_AUTH_EXCEPTION_RESPONSE_BODY = "{\n" +
					"    \"error\": \"invalid_client\",\n" +
					"    \"error_description\": \"Invalid client secret\"\n" +
					"}";
	private static final String CORRECT_KEY = "error.message";
	private static final String INVALID_KEY = "invalid";
	private static final String ACCESS_TOKEN_EXPIRED_MESSAGE = "The access token expired";
	private static final String INVALID_CLIENT_ID_ERROR_DESC = "Invalid client secret";
	private static final String INVALID_CLIENT_ID_ERROR = "invalid_client";
	private static final String USER_NOT_FOUND_MESSAGE = "User not found";
	CustomErrorResponseHandler customErrorResponseHandler;
	@Mock
	HttpStatusCodeException httpStatusCodeException;
	@Mock
	GeneralMusicDetectorException generalMusicDetectorException;
	@Mock
	AuthorizeException authorizeException;
	@Mock
	WebRequest request;

	@BeforeEach
	void initMocks() {
		customErrorResponseHandler = new CustomErrorResponseHandler();
	}

	//HTTP EXCEPTIONS
	@Test
	void when_handleHttpExceptions_should_return_same_status_code_as_exception() {
		when(httpStatusCodeException.getResponseBodyAsString()).thenReturn(HTTP_STATUS_CODE_EXCEPTION_RESPONSE_BODY);
		when(httpStatusCodeException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleHttpExceptions(httpStatusCodeException, request);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void when_handleHttpExceptions_should_return_error_message_from_exception() {
		when(httpStatusCodeException.getResponseBodyAsString()).thenReturn(HTTP_STATUS_CODE_EXCEPTION_RESPONSE_BODY);
		when(httpStatusCodeException.getStatusCode()).thenReturn(HttpStatus.UNAUTHORIZED);
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleHttpExceptions(httpStatusCodeException, request);
		final RegularErrorResponse body = (RegularErrorResponse) responseEntity.getBody();
		assertEquals(ACCESS_TOKEN_EXPIRED_MESSAGE, body.getMessage());
	}

	//GENERAL MUSIC EXCEPTIONS
	@Test
	void when_handleGeneralMusicExceptions_should_return_same_status_code_as_exception() {
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleGeneralMusicExceptions(generalMusicDetectorException, request);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

	@Test
	void when_handleGeneralMusicExceptions_should_return_error_message_from_exception() {
		when(generalMusicDetectorException.getLocalizedMessage()).thenReturn(USER_NOT_FOUND_MESSAGE);
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleGeneralMusicExceptions(generalMusicDetectorException, request);
		final RegularErrorResponse body = (RegularErrorResponse) responseEntity.getBody();
		assertEquals(USER_NOT_FOUND_MESSAGE, body.getMessage());
	}

	//AUTH EXCEPTIONS
	@Test
	void when_handleAuthenticationExceptions_should_return_same_status_code_as_exception() {
		when(authorizeException.getHttpStatusCodeException()).thenReturn(httpStatusCodeException);
		when(httpStatusCodeException.getResponseBodyAsString()).thenReturn(HTTP_AUTH_EXCEPTION_RESPONSE_BODY);
		//when(httpStatusCodeException.getStatusCode()).thenReturn(HttpStatus.UNAUTHORIZED);
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleAuthenticationExceptions(authorizeException, request);
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}

	@Test
	void when_handleAuthenticationExceptions_should_return_error_description_from_exception() {
		when(authorizeException.getHttpStatusCodeException()).thenReturn(httpStatusCodeException);
		when(httpStatusCodeException.getResponseBodyAsString()).thenReturn(HTTP_AUTH_EXCEPTION_RESPONSE_BODY);
		//when(httpStatusCodeException.getStatusCode()).thenReturn(HttpStatus.UNAUTHORIZED);
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleAuthenticationExceptions(authorizeException, request);
		final AuthenticationErrorResponse body = (AuthenticationErrorResponse) responseEntity.getBody();
		assertEquals(INVALID_CLIENT_ID_ERROR_DESC, body.getErrorDescription());
	}

	@Test
	void when_handleAuthenticationExceptions_should_return_error_from_exception() {
		when(authorizeException.getHttpStatusCodeException()).thenReturn(httpStatusCodeException);
		//when(httpStatusCodeException.getStatusCode()).thenReturn(HttpStatus.UNAUTHORIZED);
		when(httpStatusCodeException.getResponseBodyAsString()).thenReturn(HTTP_AUTH_EXCEPTION_RESPONSE_BODY);
		final ResponseEntity<Object> responseEntity = customErrorResponseHandler.handleAuthenticationExceptions(authorizeException, request);
		final AuthenticationErrorResponse body = (AuthenticationErrorResponse) responseEntity.getBody();
		assertEquals(INVALID_CLIENT_ID_ERROR, body.getError());
	}

	@Test
	void when_extractFomJson_has_incorrect_nested_keys_should_return_empty() {
		final Optional<Object> extractFomJson = customErrorResponseHandler.extractFomJson(HTTP_AUTH_EXCEPTION_RESPONSE_BODY, CORRECT_KEY);
		assertFalse(extractFomJson.isPresent());
	}

	@Test
	void when_extractFomJson_has_incorrect_key_should_return_empty() {
		final Optional<Object> extractFomJson = customErrorResponseHandler.extractFomJson(HTTP_AUTH_EXCEPTION_RESPONSE_BODY, INVALID_KEY);
		assertFalse(extractFomJson.isPresent());
	}
}