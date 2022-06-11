package com.example.sunrisegrocery.ProductData;

public class ProductData {
    public int id;
    public String name;
    public String imageUrl;
    public String desc;
    public double rating;
    public double price;
    public double discountPrice;
    public ProductData(int id, String name, String imageUrl,String desc, double rating, double price, double discountPrice){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.rating = rating;
        this.price = price;
        this.discountPrice = discountPrice;

    }
}
