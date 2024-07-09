package com.Hasindu.Online.Food.Ordering.request;

import lombok.Data;

import java.security.SecureRandom;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
