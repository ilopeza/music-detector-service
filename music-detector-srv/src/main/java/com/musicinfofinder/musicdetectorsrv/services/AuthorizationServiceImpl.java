package com.musicinfofinder.musicdetectorsrv.services;

import com.musicinfofinder.musicdetectorsrv.enums.ResponseTypeEnum;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.AuthorizeRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.request.TokenRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.TokenRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.AuthorizeResponse;
import com.musicinfofinder.musicdetectorsrv.models.response.TokenResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private final static Logger logger = LogManager.getLogger(AuthorizationServiceImpl.class);
	private final static String REDIRECT_URI = "http://localhost:8081/postAuthorize";

	@Value("${api.spotify.client.id}")
	String clientId;
	@Autowired
	RestTemplate restTemplate;
	@Value("${api.spotify.client.secret}")
	private String secretClient;

	@Override
	public void authorize() throws AuthorizeException {
		//TODO: GENERATE AN SHA TO ADD AS STATE AND THEN USE IT TO PREVENT ATTACKS
		final AuthorizeRequest request = AuthorizeRequestBuilder
						.requestBuilder()
						.withClientId(clientId)
						.withRedirectUri(REDIRECT_URI)
						.withResponseType(ResponseTypeEnum.CODE.getName())
						.withScopes(Arrays.asList("user-read-private", "user-read-email", "user-read-currently-playing",
										"user-read-playback-state"))
						.withShowDialog(false)
						//.withState(STATE)
						.build();
		final URI uri = request.getUri();
		logger.info("The uri to be opened is {}", uri.toString());
		openInBrowser(uri);
	}

	/**
	 * Open the acceptance dialog.
	 *
	 * @param url
	 * @throws AuthorizeException if the browser cannot be opened.
	 */
	//TODO: IS THERE A BETTER WAY TO OPEN THE BROWSER? MULTIPLATFORM AND WITH NO FAILURES?
	private void openInBrowser(URI url) throws AuthorizeException {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(url);
			} catch (IOException exception) {
				logger.info("Cannot open browser with Desktop since it is not supported. Trying to open thru CLI", exception);
			}
		}
		String os = System.getProperty("os.name").toLowerCase();
		Runtime rt = Runtime.getRuntime();
		try {
			final String urlToOpen = url.toString();
			if (os.indexOf("win") >= 0) {
				// this doesn't support showing urls in the form of "page.html#nameLink"
				rt.exec("rundll32 url.dll,FileProtocolHandler " + urlToOpen);
			} else if (os.indexOf("mac") >= 0) {
				rt.exec("open " + urlToOpen);
			} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
				openInUnixOS(urlToOpen, rt);
			} else {
				throw new AuthorizeException("Cannot identify OS");
			}
		} catch (Exception e) {
			throw new AuthorizeException("Cannot open dialog to authorize");
		}
	}

	private void openInUnixOS(String url, Runtime rt) throws IOException {
		// Do a best guess on unix until we get a platform independent way
		// Build a list of browsers to try, in this order.
		String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
						"netscape", "opera", "links", "lynx", "chrome"};
		// Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
		StringBuffer cmd = new StringBuffer();
		for (int i = 0; i < browsers.length; i++) {
			cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
		}
		rt.exec(new String[]{"sh", "-c", cmd.toString()});
	}

	@Override
	public String postAuthorize(AuthorizeResponse response) throws AuthorizeException {
		if (response.hasError()) {
			throw new AuthorizeException("The user has not authorized the application");
		}
		/**if (!response.isValidState(STATE)) {
		 throw new AuthorizeException("The state code is not recognized");
		 }**/

		final String code = response.getCode();
		logger.info("Response code is {}", code);
		return getToken(code).getAccessToken();
	}

	@Override
	public TokenResponse getToken(String code) {
		final TokenRequest tokenRequest = TokenRequestBuilder.requestBuilder(clientId, secretClient)
						.withCode(code)
						.withRedirectUri(REDIRECT_URI)
						.build();
		HttpEntity<Object> requestEntity = new HttpEntity<>(tokenRequest.getBody(), tokenRequest.getHeaders());
		ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(tokenRequest.getUri(), HttpMethod.POST,
						requestEntity, TokenResponse.class);
		TokenResponse result = responseEntity.getBody();
		return result;
	}

	@Override
	public TokenResponse refreshToken() {
		return null;
	}
}
