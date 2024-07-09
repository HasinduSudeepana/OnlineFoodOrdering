package com.Hasindu.Online.Food.Ordering.service;

import com.Hasindu.Online.Food.Ordering.model.USER_ROLE;
import com.Hasindu.Online.Food.Ordering.model.User;
import com.Hasindu.Online.Food.Ordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//when create CustomerUserDetailsService class, remove auto generating password in stat the springboot application.
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found this "+ username + " email");
        }

        USER_ROLE role = user.getRole() ;
        List<GrantedAuthority> authorities = new ArrayList<>();   //Represents an authority granted to an Authentication object.
        authorities.add(new SimpleGrantedAuthority(role.toString()));     //Stores a String representation of an authority granted to the Authentication object.
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
