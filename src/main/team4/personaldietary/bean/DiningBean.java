package main.team4.personaldietary.bean;

import java.time.LocalDateTime;

public class DiningBean {

    private String time;
    private String group;
    private String name;
    public DiningBean(){
        this(LocalDateTime.now().toString(),"","super");
    }

    public DiningBean(String time, String group, String name) {
        this.time = time;
        this.group = group;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDining() {
        return "";
    }

    public String getRetailer() {
        return "";
    }

    public String toString() {
        return "Name: "+name+" Group: "+group+" Time: " +time;
    }

}
