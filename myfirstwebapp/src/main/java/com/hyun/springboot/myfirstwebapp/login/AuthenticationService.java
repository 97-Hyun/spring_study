package com.hyun.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String userName, String password) {
        boolean isValidUserName = userName.equalsIgnoreCase("hyun");
        boolean isValidPassword = password.equalsIgnoreCase("test");

        return isValidUserName && isValidPassword;
    }
}
