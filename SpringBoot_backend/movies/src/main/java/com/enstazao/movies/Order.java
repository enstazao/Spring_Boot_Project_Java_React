package com.enstazao.movies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    public String id;
    private String recEmail;
    private String recAddress;
    private String recPhoneNumber;
    private String orderNumber;
    public String getId() { return id; }
    public void setId(String id) {this.id = id; }
    public String getRecEmail() { return recEmail; }

    public void setRecEmail(String recEmail) { this.recEmail = recEmail; }

    public String getRecAddress() { return recAddress; }

    public void setRecAddress(String recAddress) { this.recAddress = recAddress; }

    public String getRecPhoneNumber() { return recPhoneNumber; }

    public void setRecPhoneNumber(String recPhoneNumber) { this.recPhoneNumber = recPhoneNumber; }

    public String getOrderNumber() { return orderNumber; }

    public void setOrderNumber(String orderNumber) {this.orderNumber = orderNumber; }

}
