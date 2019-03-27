package com.identitas.view;

import com.identitas.model.User;

import java.time.Instant;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class UserView extends BaseView {

    private final User user;

    public UserView(final User user) {
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

    public String getAlias() {
        return user.getAlias();
    }

    public User.Status getStatus() {
        return user.getStatus();
    }

    public Instant getCreated() {
        return user.getCreated();
    }

    public Instant getLastModified() {
        return user.getLastModified();
    }

}
