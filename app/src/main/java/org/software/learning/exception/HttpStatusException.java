package org.software.learning.exception;

import org.software.learning.model.common.ApiResponse;

public class HttpStatusException extends Exception {
    public HttpStatusException(ApiResponse<?> response) {
        super("return Failed HttpStatusCode");
        this.response = response;
    }

    private ApiResponse<?> response;

    public ApiResponse<?> getResponse() {
        return response;
    }

    public void setResponse(ApiResponse<?> response) {
        this.response = response;
    }
}
