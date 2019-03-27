package com.identitas.exception;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class IdentitasError {
    private int code;
    private String errorKey;
    private String description;

    public int getCode() {
        return code;
    }

    public IdentitasError setCode(int code) {
        this.code = code;
        return this;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public IdentitasError setErrorKey(final String errorKey) {
        this.errorKey = errorKey;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IdentitasError setDescription(String description) {
        this.description = description;
        return this;
    }
}
