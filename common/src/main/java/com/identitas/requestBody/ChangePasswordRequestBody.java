package com.identitas.requestBody;

import java.io.Serializable;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class ChangePasswordRequestBody implements Serializable {

    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequestBody() {

    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
