package com.restaurantproject.Restaurant;

import javax.swing.*;

public class OrderItem {
    private int orderNumber;
    private String orderType;
    private String imagePath;
    private String foodName;
    private Double foodPrice;
    private int quantity;

    public OrderItem(String orderType, String image, String foodName, Double foodPrice, int quantity) {
        this.orderType = orderType;
        this.imagePath = image;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public OrderItem(int orderNumber, String orderType, String foodName, Double foodPrice, int quantity) {
        this.orderNumber = orderNumber;
        this.orderType = orderType;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public OrderItem(String orderType, String foodName, Double foodPrice, int quantity) {
        this.orderType = orderType;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public OrderItem(String foodName, Double foodPrice, int quantity) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
    }

    public int getOrderNumber() {return  this.orderNumber;};

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getImage() {
        return imagePath;
    }

    public void setImage(String image) {
        this.imagePath = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String toString() {
        return "Order Number: " + orderNumber + " Order Type: " + this.orderType + " Food Name: " + this.foodName + " Price: " + this.foodPrice + " Quantity: " + this.quantity;
    }
}