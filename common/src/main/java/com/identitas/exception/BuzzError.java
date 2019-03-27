package com.identitas.exception;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class BuzzError {

    private int code;
    private String errorKey;
    private String description;

    public int getCode() {
        return code;
    }

    public BuzzError setCode(final int code) {
        this.code = code;
        return this;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public BuzzError setErrorKey(final String errorKey) {
        this.errorKey = errorKey;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BuzzError setDescription(final String description) {
        this.description = description;
        return this;
    }
}
