package com.mawai.raclvendorscanapp.login.response;

import com.mawai.raclvendorscanapp.login.model.LoginModel;

public class LoginResponse {

    private Boolean status;
    private String message;
    private LoginModel data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginModel getData() {
        return data;
    }

    public void setData(LoginModel data) {
        this.data = data;
    }
}
