package com.musicinfofinder.musicdetectorsrv.services.authorization;

import com.musicinfofinder.musicdetectorsrv.enums.ResponseTypeEnum;
import com.musicinfofinder.musicdetectorsrv.exceptions.AuthorizeException;
import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.authorization.AuthorizeRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.AuthorizationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final static Logger logger = LogManager.getLogger(AuthorizationServiceImpl.class);
    private final static String REDIRECT_URI = "http://localhost:8081/postAuthorize";
    @Autowired
    ITokenService tokenService;
    @Value("${api.spotify.client.id}")
    private String clientId;

    @Override
    public void authorize() throws AuthorizeException, MalformedRequestException {
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
            throw new AuthorizeException("Cannot open dialog to authorize", HttpStatus.UNAUTHORIZED);
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
    public String postAuthorize(AuthorizationDTO response) throws AuthorizeException, MalformedRequestException {
        if (response.hasError()) {
            throw new AuthorizeException("The user has not authorized the application", HttpStatus.UNAUTHORIZED);
        }
        /**if (!response.isValidState(STATE)) {
         throw new AuthorizeException("The state code is not recognized");
         }**/

        String code = response.getCode();
        logger.info("Response code is {}", code);
        return tokenService.requestToken(code).getAccessToken();
    }
}
