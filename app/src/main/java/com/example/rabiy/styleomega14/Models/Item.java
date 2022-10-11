package com.example.rabiy.styleomega14.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Item extends SugarRecord<Item> {

    private int code;
    private String name;
    private String itemDescription;
    public int qtyInStock;
    private int price;
    private String category;
    private boolean newArrival;
    private String image;
    private String gender;

    //Sugar will ignore the fields with the @Ignore annotation
    @Ignore
    private List<WishlistItem> wishlistItems;
    private String wishlistItemsStore; //to store the list in JSON format
    @Ignore
    private List<Review> reviews;
    private String reviewsStore;
    @Ignore
    private List<Inquiry> inquiries;
    private String inquiriesStore;
    @Ignore
    private List<CartItem> cartItemList;
    private String cartItemsStore;

    public Item() {
    }

    public Item(String name, int price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Item(String category, String image,String gender) {
        this.category = category;
        this.image = image;
        this.gender=gender;
    }

    public Item(int code, String name, String itemDescription, int qtyInStock, int price, String category, boolean newArrival,String gender) {
        this.code = code;
        this.name = name;
        this.itemDescription = itemDescription;
        this.qtyInStock = qtyInStock;
        this.price = price;
        this.category = category;
        this.newArrival = newArrival;
        this.gender=gender;
    }

    public List<WishlistItem> getMyWishlistItems(){
        //Convert from JSON string to List
        wishlistItems=new Gson().fromJson(this.wishlistItemsStore,new TypeToken<List<WishlistItem>>(){}.getType());
        return wishlistItems;
    }

    public void setMyWishlistItems(List<WishlistItem> items){
        this.wishlistItems=items;
    }

    public List<Review> getMyReviews(){
        //Convert from JSON string to List
        reviews=new Gson().fromJson(this.reviewsStore,new TypeToken<List<Review>>(){}.getType());
        return reviews;
    }

    public void setMyReviews(List<Review> r){
        this.reviews=r;
    }

    public List<Inquiry> getMyInquiries(){
        //Convert from JSON string to List
        inquiries=new Gson().fromJson(this.inquiriesStore,new TypeToken<List<Inquiry>>(){}.getType());
        return inquiries;
    }

    public void setMyInquiries(List<Inquiry> i){
        this.inquiries=i;
    }

    public List<CartItem> getMyCartItems(){
        //Convert from JSON string to List
        cartItemList=new Gson().fromJson(this.cartItemsStore,new TypeToken<List<CartItem>>(){}.getType());
        return cartItemList;
    }

    public void setMyCartItems(List<CartItem> myItems){
        this.cartItemList=myItems;
    }

    @Override
    public void save() {
        this.wishlistItemsStore=new Gson().toJson(wishlistItems);
        this.reviewsStore=new Gson().toJson(reviews);
        this.inquiriesStore=new Gson().toJson(inquiries);
        this.cartItemsStore=new Gson().toJson(cartItemList);
        super.save();
    }


    public void setimage(String image) {
        this.image = image;
    }
    public String getimage() {
        return image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isNewArrival() {
        return newArrival;
    }

    public void setNewArrival(boolean newArrival) {
        this.newArrival = newArrival;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
