package com.pro.pcmappnew.utils;

public class User{
    public String fullname;
    public String phonenumber;
    public String password;
    public String email;

    public User() {

    }
    public User(String fullname, String email, String phonenumber) {
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}