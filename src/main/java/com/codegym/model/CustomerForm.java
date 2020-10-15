package com.codegym.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class CustomerForm {
    private Long id;
    private String name;
    private int old;
    private String address;
    private String detail;
    private MultipartFile img;
    private String phoneNumber;
    private Province province;
    public CustomerForm(){

    }

    public CustomerForm(Long id, String name, int old, String address, String detail, MultipartFile img, String phoneNumber,Province province) {
        this.id = id;
        this.name = name;
        this.old = old;
        this.address = address;
        this.detail = detail;
        this.img = img;
        this.phoneNumber = phoneNumber;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
