package com.identitas.requestBody;

import java.io.Serializable;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class UserRequestBody implements Serializable {

    private String alias;

    public UserRequestBody() {

    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }
}
