package com.enstazao.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recorders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecOrder {
    @Id
    private String id;
    private String driverEmail;

    private String recEmail;

    private String recAddress;

    private String recPhoneNumber;

    private String orderNumber;

    public String getRecPhoneNumber() { return recPhoneNumber;}
    public void setRecPhoneNumber(String phoneNumber) { this.recPhoneNumber = recPhoneNumber;}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) { this.driverEmail = driverEmail; }

    public String getRecEmail() {
        return recEmail;
    }

    public void setRecEmail(String recEmail) { this.recEmail = recEmail; }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) { this.recAddress = recAddress; }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    // getters and setters
}
