package com.example.myapplication.MODEL;

import java.util.Map;

public class Sanpham {
    private String masp;
    private String name;
    private double price;
    private int time_ship;
    private String describe;
    private int amount;

    private boolean favorite;
    private String imgURL;
    private Map<String, Comment> comments;
    private String ten_loai;
    public Sanpham() {
    }

    public Sanpham(String masp, String name, double price, int time_ship, String describe, int amount, boolean favorite, String imgURL, Map<String, Comment> comments,String ten_loai) {
        this.masp = masp;
        this.name = name;
        this.price = price;
        this.time_ship = time_ship;
        this.describe = describe;
        this.amount = amount;
        this.favorite = favorite;
        this.imgURL = imgURL;
        this.comments = comments;
        this.ten_loai  = ten_loai;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTime_ship() {
        return time_ship;
    }

    public void setTime_ship(int time_ship) {
        this.time_ship = time_ship;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<String, Comment> comments) {
        this.comments = comments;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }
}