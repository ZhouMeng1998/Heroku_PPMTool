package io.mengzhou.ppmtool.payload;

public class JWTLoginSucessReponse {
    private String token;
    private boolean success;

    public JWTLoginSucessReponse(String token, boolean success) {
        this.token = token;
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "token='" + token + '\'' +
                ", success=" + success +
                '}';
    }
}
