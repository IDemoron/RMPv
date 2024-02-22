package com.example.pr2;

public class Church {
    private int id;
    private String name;
    private String address;
    private String hours;
    private String img;

    public Church(int id, String name, String address, String hours, String img){
        this.id = id;
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getHours() {
        return hours;
    }

    public String getImg() {
        return img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
