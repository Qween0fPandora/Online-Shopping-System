package model;

import java.util.ArrayList;
import java.util.UUID;

public class Order {
    String orderId;
    String paymentMethod;
    double paymentAmount;
    String deliveryAddress;
    ArrayList<Product> productList;
    String email;

    public Order(String orderId, String paymentMethod, double paymentAmount, String deliveryAddress, ArrayList<Product> productList, String email) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.deliveryAddress = deliveryAddress;
        this.productList = productList;
        this.email = email;
    }

    // Getters
    public String getOrderId() {
//        return orderId;
        return UUID.randomUUID().toString();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
    public String getEmail() {return email;}

    // Setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void setEmail(String e) {this.email = e;}
    public static void printOrderList(ArrayList<Order> orderList){
        for(Order order: orderList){
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Payment Method: " + order.getPaymentMethod());
            System.out.println("Payment Amount: " + order.getPaymentAmount());
            System.out.println("Delivery Address: " + order.getDeliveryAddress());
            System.out.println("Product List: ");
            Product.printProductList(order.getProductList());
            System.out.println("--------------------------");
        }
    }
}

