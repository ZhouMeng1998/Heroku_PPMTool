package io.mengzhou.ppmtool.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "username can't be blank")
    private String username;
    @NotBlank(message = "password can't be blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
