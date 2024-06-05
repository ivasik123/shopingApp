package com.example.shoppingapp;

public class Products {
    private int id;
    private final int image;
    private String name;
    private final double price;
    private final String brand;
    private String description;
    private double discount;
    public Products(int id, int image, String name, double price, String brand, String description, double discount) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.discount = discount;
    }

    public Products(int image, String name, double price, String brand) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public Products(int image, String name, double price, String brand, String description, double discount) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getImage() {
        return image;
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
    public String getBrand() {
        return brand;
    }
    public String getDescription() {
        return description;
    }
    public double getDiscount() {
        return discount;
    }
}
