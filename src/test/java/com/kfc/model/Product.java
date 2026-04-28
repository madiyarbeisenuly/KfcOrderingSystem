package com.kfc.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private String description;

    public Product(int id, String name, double price, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " - " + price + " ₸";
    }
}