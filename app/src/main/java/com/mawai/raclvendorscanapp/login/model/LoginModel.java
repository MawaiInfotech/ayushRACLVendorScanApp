package com.mawai.raclvendorscanapp.login.model;

public class LoginModel {


    private String vendorcode;
    private String password;
    private String vendorname;


    public LoginModel() {
    }

    public LoginModel(String vendorcode, String password) {
        this.vendorcode = vendorcode;
        this.password = password;
    }

    public String getVendorcode() {
        return vendorcode;
    }

    public void setVendorcode(String vendorcode) {
        this.vendorcode = vendorcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }
}
