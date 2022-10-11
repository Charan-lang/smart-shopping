package com.example.rabiy.styleomega14.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class User extends SugarRecord<User> {

    private String lName,fName,phoneNo,address,email,dob,password,payment_cardNo,payment_cardExp;
    private int payment_cardCode;
    private Cart cart;

    //Sugar will ignore the fields with the @Ignore annotation
    @Ignore
    private List<WishlistItem> wishlistItems;
    private String wishlistItemsStore;
    @Ignore
    private List<Review> reviews;
    private String reviewsStore;
    @Ignore
    private List<Inquiry> inquiries;
    private String inquiriesStore;


    public User() {
    }

    public User( String fName,String lName, String phoneNo, String email, String dob, String password) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dob = dob;
        this.password = password;
    }

    public User(String address, String payment_cardNo, String payment_cardExp, int payment_cardCode) {
        this.address = address;
        this.payment_cardNo = payment_cardNo;
        this.payment_cardExp = payment_cardExp;
        this.payment_cardCode = payment_cardCode;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Inquiry> getInquiries() {
        return inquiries;
    }

    public void setInquiries(List<Inquiry> inquiries) {
        this.inquiries = inquiries;
    }

    public String getPayment_cardNo() {
        return payment_cardNo;
    }

    public void setPayment_cardNo(String payment_cardNo) {
        this.payment_cardNo = payment_cardNo;
    }

    public String getPayment_cardExp() {
        return payment_cardExp;
    }

    public void setPayment_cardExp(String payment_cardExp) {
        this.payment_cardExp = payment_cardExp;
    }

    public int getPayment_cardCode() {
        return payment_cardCode;
    }

    public void setPayment_cardCode(int payment_cardCode) {
        this.payment_cardCode = payment_cardCode;
    }
}
