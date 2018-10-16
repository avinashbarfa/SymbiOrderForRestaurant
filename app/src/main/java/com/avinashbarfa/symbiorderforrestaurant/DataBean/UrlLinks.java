package com.avinashbarfa.symbiorderforrestaurant.DataBean;

public class UrlLinks {

    private  String serverIP = "http://192.168.43.72";
    private  String AuthenticateLink = getServerIP()+"/SymbiOrder_Backend/authenticateRestaurant.php";
    private String getOrdersLink = getServerIP()+"/SymbiOrder_Backend/get-orders.php";
    private String UpdateOrderDetail = getServerIP()+"/SymbiOrder_Backend/update-order-details.php";


    public String getServerIP() {
        return serverIP;
    }

    public String getAuthenticateLink() {
        return AuthenticateLink;
    }

    public String getGetOrdersLink() {
        return getOrdersLink;
    }

    public String getUpdateOrderDetail() {
        return UpdateOrderDetail;
    }
}
