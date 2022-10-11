package com.example.rabiy.styleomega14.Models;

import com.orm.SugarRecord;

public class WishlistItem extends SugarRecord {

    User user;
    Item item;

    public WishlistItem() {
    }

    public WishlistItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
