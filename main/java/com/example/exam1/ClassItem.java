package com.example.exam1;
import java.io.Serializable;

public class ClassItem implements  Serializable{
    private String name;
    private String time;

    public  ClassItem(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public  String getName() {
        return  name;
    }

    public  String getTime() {
        return time;
    }
}
