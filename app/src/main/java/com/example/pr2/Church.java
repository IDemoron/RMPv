package com.example.pr2;

public class Church {
    private int id;
    private  String religion;
    private String name;
    private String address;
    private String hours;
    private String img;
    private int fav;

    public Church(int id, String religion, String name, String address, String hours, String img, int fav){
        this.id = id;
        this.religion = religion;
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.img = img;
        this.fav = fav;
    }

    public int getId() {
        return id;
    }
    public String getReligion() {
        return religion;
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
    public int getFav() {
        return fav;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setReligion(String religion) {
        this.religion = religion;
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
    public void setFav(int fav) {
        this.fav = fav;
    }
}
