package com.enstazao.movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired
    private LoginInterface loginInterface;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Find the user by email
        Register user = loginInterface.findByEmailAndPassword(email, password);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // User exists and password matches, return user info
            LoginResponse loginResponse = new LoginResponse(user.getUserType(), user.getFirstName(), user.getLastName(), user.getEmail());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else {
            // User does not exist or password does not match
            Map<String, String> response = new HashMap<>();
            response.put("message", "User does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
