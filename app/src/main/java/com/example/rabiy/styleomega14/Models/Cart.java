package com.example.rabiy.styleomega14.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Cart extends SugarRecord<Cart> {

    private int totalAmount;
    private String date,status;
    private User user;

    @Ignore
    private List<CartItem> cartItemList;
    private String cartItemsStore;

    public Cart() {
    }

    public Cart(  String status, User user) {
        this.status = status;
        this.user = user;
    }

    public Cart( int totalAmount, String date, String status) {
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
