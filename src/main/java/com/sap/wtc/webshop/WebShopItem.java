package com.sap.wtc.webshop;

public class WebShopItem {
    private int id;
    private String category;
    private String name;
    private double price;
    
    public WebShopItem() {
    
    }
    
    public WebShopItem(long id, String name, double price, String category) {
        this.id = (int) id;
        this.category = category;
        this.name = name;
        this.price = price;
    }
    
    public WebShopItem(int id, String name, double price, String category) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }
    
    public WebShopItem(int id, double price, String category) {
        this.id = id;
        this.category = category;
        this.price = price;
    }
    
    public WebShopItem(int id, double price) {
        this.id = id;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
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
}
