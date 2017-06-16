package com.myapplicationdev.android.msarevision;

import java.io.Serializable;

/**
 * Created by jason_lim on 14/6/2017.
 */

public class Contact  implements Serializable{

    private String name;
    private int id;
    private double height;
    private String gender;

    public Contact(String name, int id, double height, String gender) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public Contact setHeight(double height) {
        this.height = height;
        return this;
    }

    public Contact setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
