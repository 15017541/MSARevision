package com.myapplicationdev.android.msarevision;

/**
 * Created by jason_lim on 14/6/2017.
 */

public class Contact  {

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

}
