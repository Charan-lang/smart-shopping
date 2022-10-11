package com.example.rabiy.styleomega14.Models;

import com.orm.SugarRecord;

public class CartItem extends SugarRecord<CartItem> {

    int quantity;
    Item item;
    Cart cart;
    String size;
    String color;

    public CartItem() {
    }

    public CartItem(Item item, Cart cart) {
        this.item = item;
        this.cart = cart;
    }

    public CartItem(int quantity, Item item, Cart cart,String size,String color) {
        this.quantity = quantity;
        this.item = item;
        this.cart = cart;
        this.size=size;
        this.color=color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
