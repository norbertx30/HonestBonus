package com.github.groupproject.dto;

import org.hibernate.validator.constraints.Email;

public class SetPasswordDto {


    private String uuid;
    private String password;
    private String repeatPassword;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SetPasswordDto{" +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}