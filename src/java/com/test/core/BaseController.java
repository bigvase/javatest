package com.test.core;

public class BaseController {
    private String flash = "admin";

    public BaseController(String flash) {
        this.flash = flash;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }


    protected  
}
