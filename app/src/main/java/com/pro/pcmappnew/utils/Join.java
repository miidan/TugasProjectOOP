package com.pro.pcmappnew.utils;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Join {
    public String name;
    public String idnumber;
    public String email;
    public String phonenumber;
    public String address;
    public String birthdate;

    public Join() {
    }

    public Join(String name, String idnumber, String email, String phonenumber, String address, String birthdate) {
        this.name = name;
        this.idnumber = idnumber;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.birthdate = birthdate;
    }
}