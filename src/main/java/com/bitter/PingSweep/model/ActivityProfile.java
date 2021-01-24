package com.bitter.PingSweep.model;

import java.util.ArrayList;
import java.util.List;

public class ActivityProfile {

    private String name;
    private String address;
    private ArrayList<ArrayList<String>> weekActivity = new ArrayList<>(24);

    public ActivityProfile(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ArrayList<ArrayList<String>> getWeekActivity() {
        return weekActivity;
    }
    public void setWeekActivity(ArrayList<ArrayList<String>> weekActivity) {
        this.weekActivity = weekActivity;
    }

    @Override
    public String toString() {
        return "ActivityProfile{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", weekActivity=" + weekActivity+
                '}';
    }
}