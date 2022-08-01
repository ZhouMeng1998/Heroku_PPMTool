package io.mengzhou.ppmtool.exception;

public class UsernameRepeatExceptionResponse {
    private String usernameRepeat;

    public UsernameRepeatExceptionResponse(String usernameRepeat) {
        this.usernameRepeat = usernameRepeat;
    }

    public String getUsernameRepeat() {
        return usernameRepeat;
    }

    public void setUsernameRepeat(String usernameRepeat) {
        this.usernameRepeat = usernameRepeat;
    }
}
