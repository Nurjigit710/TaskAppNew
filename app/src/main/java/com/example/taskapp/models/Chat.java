package com.example.taskapp.models;

import java.io.Serializable;

public class Chat implements Serializable {
    public String name;
    public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
