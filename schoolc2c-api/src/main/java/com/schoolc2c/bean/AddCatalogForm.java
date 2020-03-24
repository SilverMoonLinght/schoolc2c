package com.schoolc2c.bean;

import java.io.Serializable;
import java.util.List;

public class AddCatalogForm implements Serializable {
    private String pid;
    private String name;
    private int level;
    private List<ProductCatalog2> catalog;

    public List<ProductCatalog2> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<ProductCatalog2> catalog) {
        this.catalog = catalog;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
