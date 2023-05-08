package com.enstazao.movies;
public class LoginResponse {
    private String userType;
    private String firstName;
    private String lastName;
    private String email;

    public LoginResponse(String userType, String firstName, String lastName, String email) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {return this.email; }
    public void setEmail() {this.email = email; }

}
