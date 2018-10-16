package com.avinashbarfa.symbiorderforrestaurant.DataBean;

public class OrdersDataBean {

    private int OrderID, status, amount;
    private long contactNumber;
    private String items,address,timestamp;

    public OrdersDataBean(int orderID, int status, int amount, long contactNumber, String items, String address, String timestamp) {
        OrderID = orderID;
        this.status = status;
        this.amount = amount;
        this.contactNumber = contactNumber;
        this.items = items;
        this.address = address;
        this.timestamp = timestamp;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getStatus() {
        return status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public String getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
