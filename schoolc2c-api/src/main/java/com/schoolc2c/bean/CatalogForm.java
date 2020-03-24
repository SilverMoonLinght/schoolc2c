package com.schoolc2c.bean;

import java.io.Serializable;
import java.util.List;

public class CatalogForm implements Serializable {


    private String id;
    private String name;
    private String pid;
    private int level;
    private List<ProductCatalog2> catalog;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<ProductCatalog2> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<ProductCatalog2> catalog) {
        this.catalog = catalog;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
