package com.enstazao.movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    LoginInterface loginInterface;

    @Test
    public void testLoginSuccess() {
        // Mock data
        Register user = new Register();
        user.setUserType("user");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");

        // Mock repository
        when(loginInterface.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);

        // Mock request
        Login loginRequest = new Login();
        loginRequest.setEmail("johndoe@example.com");
        loginRequest.setPassword("password");

        // Call API
        ResponseEntity<Object> responseEntity = loginController.login(loginRequest);

        // Check response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        LoginResponse loginResponse = (LoginResponse) responseEntity.getBody();
        assertEquals("user", loginResponse.getUserType());
        assertEquals("John", loginResponse.getFirstName());
        assertEquals("Doe", loginResponse.getLastName());
        assertEquals("johndoe@example.com", loginResponse.getEmail());
    }




}
