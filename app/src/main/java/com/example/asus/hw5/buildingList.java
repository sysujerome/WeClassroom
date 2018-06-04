package com.example.asus.hw5;

/**
 * Created by ASUS on 2017/10/22.
 */

public class buildingList {
    private String name;
    private int capacity;
    private String price;
    private String type;
    private String data;
    private int src;
    public buildingList(String a, int b, int c, String d, String e, String f) {
        name = a;
        capacity = b;
        src = c;
        type = d;
        data = e;
        price = f;
    }
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getPrice() {
        return  price;
    }
    public String getType() {
        return type;
    }
    public String getData() {
        return data;
    }
    public int getSrc() {
        return src;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
