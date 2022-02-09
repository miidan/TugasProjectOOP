package com.pro.pcmappnew.utils;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Order {
    public String name;
    public String email;
    public String phonenumber;
    public String ordertype;
    public String orderdetail;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Order() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(String orderdetail) {
        this.orderdetail = orderdetail;
    }

    public Order(String name, String email, String phonenumber, String ordertype, String orderdetail) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.ordertype = ordertype;
        this.orderdetail = orderdetail;
    }
}
