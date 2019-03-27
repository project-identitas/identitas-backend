package com.identitas.view;

import com.identitas.exception.BuzzError;

import java.time.Instant;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class BaseView {
    private BuzzError error;
    private long currentTime;

    public BaseView() {
        currentTime = Instant.now().toEpochMilli();
    }

    public BuzzError getError() {
        return error;
    }

    public BaseView setError(final BuzzError error) {
        this.error = error;
        return this;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public BaseView setCurrentTime(final long currentTime) {
        this.currentTime = currentTime;
        return this;
    }
}
