package com.Hasindu.Online.Food.Ordering.service;

import com.Hasindu.Online.Food.Ordering.config.JwtProvider;
import com.Hasindu.Online.Food.Ordering.model.User;
import com.Hasindu.Online.Food.Ordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = new User();
        user= userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);

        return user;
    }
}
