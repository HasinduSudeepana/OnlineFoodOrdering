package com.Hasindu.Online.Food.Ordering.service;

import com.Hasindu.Online.Food.Ordering.model.User;

public interface UserService {
    public User findUserByEmail(String email) throws Exception;

    public User findUserByJwtToken(String jwt) throws Exception;
}
