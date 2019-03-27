package com.identitas.exception;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class IdentitasException extends RuntimeException {

    private final ResponseError responseError;

    public IdentitasException(final ResponseError responseError) {
        super(responseError.getErrorDescription());
        this.responseError = responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }
}
