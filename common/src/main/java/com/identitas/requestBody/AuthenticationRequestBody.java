package com.identitas.requestBody;

import java.io.Serializable;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class AuthenticationRequestBody implements Serializable {

    private String email;
    private String password;

    public AuthenticationRequestBody() {

    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
