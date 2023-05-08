package com.enstazao.movies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class Feedback {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String userType;
    private String feedback;
    private String email;
    public void setId(String id) {this.id = id;}
    public String getId() { return id; }
    public String getFirstName() {return firstName; }
    public void setFirstName(String firstName) {this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email  = email; }
}
