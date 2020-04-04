package com.schoolc2c.bean;

import javax.persistence.*;
import java.io.Serializable;

public class Booth implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String id;

    @Column
    private String uid;

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
