package com.identitas.exception;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class BuzzException extends RuntimeException {

    private final ResponseError responseError;

    public BuzzException(final ResponseError responseError) {
        super(responseError.getErrorDescription());
        this.responseError = responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }
}
