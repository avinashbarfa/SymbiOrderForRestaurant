package com.avinashbarfa.symbiorderforrestaurant.DataBean;

public class RestaurantDataBean {

    private String restaurantName,restaurantPassword;
    private int restaurantID,restaurantUserID;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getRestaurantUserID() {
        return restaurantUserID;
    }

    public void setRestaurantUserID(int restaurantUserID) {
        this.restaurantUserID = restaurantUserID;
    }

    public String getRestaurantPassword() {
        return restaurantPassword;
    }

    public void setRestaurantPassword(String restaurantPassword) {
        this.restaurantPassword = restaurantPassword;
    }
}
