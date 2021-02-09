package com.example.demo1.model;

import javax.persistence.Table;

@Table(name = "av_oc")
public enum Occupancy {
    SINGLE("single"),
    DOUBLE("double");

    String name;

    Occupancy(String name) {
        this.name = name;
    }
}
