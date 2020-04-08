package com.schoolc2c.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class Booth implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String id;

    @Column
    private String uid;

    @Transient
    private String nickname;

    @Transient
    private String personalizedSignature;

    @Transient
    private String phone;

    @Transient
    private String icon;

    @Transient
    private List<BoothProduct> boothProductList;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BoothProduct> getBoothProductList() {
        return boothProductList;
    }

    public void setBoothProductList(List<BoothProduct> boothProductList) {
        this.boothProductList = boothProductList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
