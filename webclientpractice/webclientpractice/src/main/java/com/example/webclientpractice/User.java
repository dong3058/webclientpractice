package com.example.webclientpractice;

import lombok.Data;

import java.util.Map;

@Data
public class User {

    private String code;
    private String message;
    private Map<String, String> user;

    public User() {
    }

    public User(String code, String message, Map<String, String> user) {
        this.code = code;
        this.message = message;
        this.user = user;
    }
}
