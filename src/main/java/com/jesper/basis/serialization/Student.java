package com.jesper.basis.serialization;

import java.io.Serializable;

/**
 * Created by jiangyunxiong on 2018/7/16.
 */
public  class Student implements Serializable {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}