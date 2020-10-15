package com.codegym.model;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Customer implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int old;
    private String address;
    private String detail;
    private String img;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    public Customer() {

    }
    public Customer(String name, int old, String address, String detail, String phoneNumber){
        this.name = name;
        this.old = old;
        this.address = address;
        this.detail = detail;
        this.phoneNumber = phoneNumber;
    }


    public Customer(Long id, String name, int old, String address, String detail, String img, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.old = old;
        this.address = address;
        this.detail = detail;
        this.img = img;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, int old, String address, String detail, String img, String phoneNumber) {
        this.name = name;
        this.old = old;
        this.address = address;
        this.detail = detail;
        this.img = img;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, int old, String address, String detail, String phoneNumber, Province province) {
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
    public String getImg(){
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String  getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String  phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", old=" + old +
                ", address='" + address + '\'' +
                ", detail='" + detail + '\'' +
                ", img='" + img + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }
//chua hoan thanh
    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        String phoneNumber = customer.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors,"phoneNumber","phoneNumber.empty");
        if(phoneNumber.length() >11 || phoneNumber.length() <10) {
            errors.rejectValue("phoneNumber","phoneNumber.length");
        }
        if(!phoneNumber.startsWith("0")){
            errors.rejectValue("phoneNumber","phoneNumber.startsWith");

        }
        if(!phoneNumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber","phoneNumber.matches");
        }

    }
}
