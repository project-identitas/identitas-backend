package com.identitas;

import com.identitas.exception.IdentitasError;

import java.time.Instant;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class BaseResponse {
    private IdentitasError error;
    private long currentTime;

    public BaseResponse() {
        currentTime = Instant.now().toEpochMilli();
    }

    public IdentitasError getError() {
        return error;
    }

    public BaseResponse setError(final IdentitasError error) {
        this.error = error;
        return this;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public BaseResponse setCurrentTime(final long currentTime) {
        this.currentTime = currentTime;
        return this;
    }

}
