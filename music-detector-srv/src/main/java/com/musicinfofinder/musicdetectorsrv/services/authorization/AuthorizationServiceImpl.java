package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.config.SpotifyCredentials;
import com.musicinfofinder.musicdetectorsrv.enums.ResponseTypeEnum;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

import static java.util.Arrays.asList;

@Service
@Slf4j
public class AuthorizationServiceImpl implements IAuthorizationService {

    private static final String REDIRECT_URI = "http://localhost:8081/postAuthorize";
    private final ITokenService tokenService;
    private final SpotifyCredentials spotifyCredentials;

    public AuthorizationServiceImpl(ITokenService tokenService, SpotifyCredentials spotifyCredentials) {
        this.tokenService = tokenService;
        this.spotifyCredentials = spotifyCredentials;
    }

    @Override
    public void authorize() throws AuthorizeException, MalformedRequestException {
        //TODO: GENERATE AN SHA TO ADD AS STATE AND THEN USE IT TO PREVENT ATTACKS
        final AuthorizeRequest request = AuthorizeRequestBuilder
                .requestBuilder()
                .withClientId(spotifyCredentials.getClientId())
                .withRedirectUri(REDIRECT_URI)
                .withResponseType(ResponseTypeEnum.CODE.getName())
                .withScopes(asList("user-read-private",
                        "user-read-email", "user-read-currently-playing",
                        "user-read-playback-state"))
                .withShowDialog(false)
                //.withState(STATE)
                .build();
        final URI uri = request.getUri();
        log.info("The uri to be opened is {}", uri.toString());
        openInBrowser(uri);
    }

    /**
     * Open the acceptance dialog.
     *
     * @param url url to open authorization dialog
     * @throws AuthorizeException if the browser cannot be opened.
     */
    //TODO: IS THERE A BETTER WAY TO OPEN THE BROWSER? MULTIPLATFORM AND WITH NO FAILURES?
    private void openInBrowser(URI url) throws AuthorizeException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(url);
            } catch (IOException exception) {
                log.error("Cannot open browser with Desktop since it is not supported. Trying to open thru CLI", exception);
            }
        }
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        try {
            final String urlToOpen = url.toString();
            if (os.contains("win")) {
                // this doesn't support showing urls in the form of "page.html#nameLink"
                rt.exec("rundll32 url.dll,FileProtocolHandler " + urlToOpen);
            } else if (os.contains("mac")) {
                rt.exec("open " + urlToOpen);
            } else if (os.contains("nix") || os.contains("nux")) {
                openInUnixOS(urlToOpen, rt);
            } else {
                throw new AuthorizeException("Cannot identify OS");
            }
        } catch (Exception e) {
            throw new AuthorizeException("Cannot open dialog to authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    private void openInUnixOS(String url, Runtime rt) throws IOException {
        // Do a best guess on unix until we get a platform independent way
        // Build a list of browsers to try, in this order.
        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                "netscape", "opera", "links", "lynx", "chrome"};
        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
        StringBuilder cmd = new StringBuilder();
        for (int i = 0; i < browsers.length; i++) {
            cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append(url).append("\" ");
        }
        rt.exec(new String[]{"sh", "-c", cmd.toString()});
    }

    @Override
    public String postAuthorize(AuthorizationDTO response) throws AuthorizeException, MalformedRequestException {
        log.debug("Starting call to post authorize with authorization {}", response);
        if (response.hasError()) {
            log.error("User not authorized with credentials {}", response);
            throw new AuthorizeException("The user has not authorized the application", HttpStatus.UNAUTHORIZED);
        }
//        *if (!response.isValidState(STATE)) {
//         throw new AuthorizeException("The state code is not recognized");
//         }
        String code = response.getCode();
        log.info("Response code is {}", code);
        return tokenService.requestToken(code).getAccessToken();
    }
}
