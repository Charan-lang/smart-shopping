package com.example.rabiy.styleomega14.Models;

import com.orm.SugarRecord;

public class Review extends SugarRecord<Review> {

    int reviewID,rating;
    String review;
    User user;
    Item item;

    public Review() {
    }

    public Review(String review,User user,Item item) {
        this.review = review;
        this.user=user;
        this.item=item;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
