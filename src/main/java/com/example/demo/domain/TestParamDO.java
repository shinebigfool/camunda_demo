package com.example.demo.domain;

import java.io.Serializable;

/**
 * @author: NXY
 * @date: 2022-01-04 11:13
 **/

public class TestParamDO implements Serializable {
    private String id;
    private String name;

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

    @Override
    public String toString() {
        return "TestParamDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
