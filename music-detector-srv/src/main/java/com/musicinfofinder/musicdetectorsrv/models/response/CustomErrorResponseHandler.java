package com.musicinfofinder.musicdetectorsrv.models.response;

import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizationSpotifyRestApiException;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.GeneralMusicDetectorException;
import com.musicinfofinder.musicdetectorsrv.models.response.error.AuthenticationErrorResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.error.RegularErrorResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;

import static com.musicinfofinder.musicdetectorsrv.models.response.error.RegularErrorResponse.RegularErrorResponseBuilder.aRegularErrorResponse;
import static java.util.Objects.isNull;

/**
 * Handles the exceptions to show custom responses with an adequate level of details.
 */
@ControllerAdvice
public class CustomErrorResponseHandler extends ResponseEntityExceptionHandler {

	public static final String ERROR_MESSAGE = "error.message";
	private static final Logger logger = LogManager.getLogger(CustomErrorResponseHandler.class);
	private static final String ERROR_KEY = "error";
	private static final String ERROR_DESCRIPTION_KEY = "error_description";

	/**
	 * Handles the response to the exceptions thrown when calling a REST API thru RestTemplate. The message and the status
	 * are retrieved from the response.
	 *
	 * @param exception
	 * @param request
	 * @return RegularErrorResponse with information about the failed call.
	 */
	@ExceptionHandler({HttpServerErrorException.class, HttpClientErrorException.class})
	public ResponseEntity<Object> handleHttpExceptions(HttpStatusCodeException exception, WebRequest request) {
		final String message = String.valueOf(extractFomJson(exception.getResponseBodyAsString(), ERROR_MESSAGE)
						.orElseGet(() -> Optional.of(exception.getLocalizedMessage())));
		final RegularErrorResponse regularErrorResponse = aRegularErrorResponse()
						.withMessage(message)
						.withStatus(exception.getStatusCode())
						.build();
		return new ResponseEntity(regularErrorResponse, exception.getResponseHeaders(), exception.getStatusCode());
	}

	/**
	 * Handles the response to most generic exceptions caused by an internal error (user not found, for example).
	 *
	 * @param exception
	 * @param request
	 * @return RegularErrorResponse with information about the exception.
	 */
	@ExceptionHandler({GeneralMusicDetectorException.class})
	public ResponseEntity<Object> handleGeneralMusicExceptions(GeneralMusicDetectorException exception, WebRequest request) {
		String message = exception.getLocalizedMessage();
		HttpStatus status = isNull(exception.getHttpStatus()) ? HttpStatus.INTERNAL_SERVER_ERROR : exception.getHttpStatus();

		final RegularErrorResponse regularErrorResponse = aRegularErrorResponse()
						.withMessage(message)
						.withStatus(status)
						.build();
		return new ResponseEntity(regularErrorResponse, regularErrorResponse.getStatus());
	}

	/**
	 * Handles only the response to exceptions thrown due to authentication issues.
	 *
	 * @param exception
	 * @param request
	 * @return AuthenticationErrorResponse entity with information about the authentication issue with status HttpStatus.UNAUTHORIZED
	 */
	@ExceptionHandler({AuthorizationSpotifyRestApiException.class})
	public ResponseEntity<Object> handleAuthenticationExceptions(AuthorizeException exception, WebRequest request) {
		String errorMessage = exception.getLocalizedMessage();
		String error = exception.getLocalizedMessage();

		//this should always be an HttpStatusCodeException but just in case...
		if (exception.getCause() instanceof HttpStatusCodeException) {
			//if it is an HttpStatusCodeException, extract the message from the causing exception.
			final HttpStatusCodeException statusCodeException = (HttpStatusCodeException) exception.getCause();
			errorMessage = String.valueOf(extractFomJson(statusCodeException.getResponseBodyAsString(), ERROR_DESCRIPTION_KEY)
					.orElseGet(() -> Optional.of(exception.getLocalizedMessage())));
			error = String.valueOf(extractFomJson(statusCodeException.getResponseBodyAsString(), ERROR_KEY)
					.orElseGet(() -> Optional.of(exception.getLocalizedMessage())));
		}

		final AuthenticationErrorResponse regularErrorResponse = AuthenticationErrorResponse.AuthenticationErrorResponseBuilder.anAuthenticationError()
						.withError(error)
						.withErrorDescription(errorMessage)
						.build();

		return new ResponseEntity(regularErrorResponse, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Extracts an Optional object detailed in the keys param as a string separated by "."
	 *
	 * @param message Raw message in Json format
	 * @param keys    Separated path to get the attribute from the message
	 * @return Optional object with the desired value or Optional.empty() if the value does not exist.
	 */
	public Optional<Object> extractFomJson(String message, String keys) {
		LinkedHashMap<String, Object> rawErrorMap;
		try {
			JSONParser parser = new JSONParser(message);
			rawErrorMap = parser.parseObject();
		} catch (ParseException e) {
			logger.error("Could not parse the message {} with keys {}", message, keys);
			return Optional.empty();
		}
		return extractFomMap(rawErrorMap, keys);
	}

	private Optional<Object> extractFomMap(LinkedHashMap map, String keys) {
		Object result;
		String currentKey = StringUtils.substringBefore(keys, ".");
		if (!map.containsKey(currentKey)) {
			return Optional.empty();
		}
		result = map.get(currentKey);
		//get the keys for next round, if that's possible
		String remainingKeys = StringUtils.substringAfter(keys, ".");
		if (StringUtils.isBlank(remainingKeys)) {
			//if there are no more keys, this should be the result
			return Optional.of(result);
		}
		//if there are still keys to find but there are no more maps, there is an error
		//and should return empty
		if (!result.getClass().isAssignableFrom(LinkedHashMap.class)) {
			return Optional.empty();
		}
		return extractFomMap((LinkedHashMap) result, remainingKeys);
	}
}
