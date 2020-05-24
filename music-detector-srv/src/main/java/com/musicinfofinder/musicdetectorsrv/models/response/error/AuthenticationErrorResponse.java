package com.musicinfofinder.musicdetectorsrv.models.response.error;

/**
 * Object to display a custom response when authentication fails
 * See https://developer.spotify.com/documentation/web-api/#authentication-error-object}
 */
public class AuthenticationErrorResponse {
    private String error;
    private String errorDescription;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public static final class AuthenticationErrorResponseBuilder {
        private String error;
        private String errorDescription;

        private AuthenticationErrorResponseBuilder() {
        }

        public static AuthenticationErrorResponseBuilder anAuthenticationError() {
            return new AuthenticationErrorResponseBuilder();
        }

        public AuthenticationErrorResponseBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public AuthenticationErrorResponseBuilder withErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        public AuthenticationErrorResponse build() {
            AuthenticationErrorResponse authenticationErrorResponse = new AuthenticationErrorResponse();
            authenticationErrorResponse.error = this.error;
            authenticationErrorResponse.errorDescription = this.errorDescription;
            return authenticationErrorResponse;
        }
    }
}
