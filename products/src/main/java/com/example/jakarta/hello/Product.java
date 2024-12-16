package com.example.jakarta.hello;
public class Product {
    private String name;
    private int price;
    private int id;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = truncateName(name);
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = truncateName(name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    private String truncateName(String name) {
        if (name == null) {
            return null;
        }
        return name.length() > 100 ? name.substring(0, 100) : name;
    }
}
