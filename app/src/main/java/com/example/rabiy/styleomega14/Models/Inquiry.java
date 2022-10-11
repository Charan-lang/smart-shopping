package com.example.rabiy.styleomega14.Models;

import com.orm.SugarRecord;

public class Inquiry extends SugarRecord {

    String inquiry;
    String reply;
    User user;
    Item item;

    public Inquiry() {
    }

    public Inquiry(String inquiry, User user, Item item) {
        this.inquiry = inquiry;
        this.user = user;
        this.item = item;
    }

    public Inquiry(String inquiry, String reply) {
        this.inquiry = inquiry;
        this.reply = reply;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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
